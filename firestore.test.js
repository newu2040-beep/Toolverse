const {
  initializeTestEnvironment,
  assertFails,
  assertSucceeds,
} = require("@firebase/rules-unit-testing");
const { test, before, after, beforeEach } = require("node:test");
const fs = require("node:fs");

let testEnv;
const PROJECT_ID = process.env.GCP_PROJECT || "demo-no-project";
const ALICE_UID = "alice_123";
const BOB_UID = "bob_456";

const [emulatorHost, emulatorPortStr] = (process.env.FIRESTORE_EMULATOR_HOST || "127.0.0.1:8085").split(":");
const emulatorPort = parseInt(emulatorPortStr, 10);

before(async () => {
  const rules = fs.readFileSync("./firestore.rules", "utf8");
  testEnv = await initializeTestEnvironment({
    projectId: PROJECT_ID,
    firestore: {
      rules,
      host: emulatorHost,
      port: emulatorPort,
    },
  });
});

after(async () => {
  if (testEnv) {
    await testEnv.cleanup();
  }
});

beforeEach(async () => {
  if (testEnv) {
    await testEnv.clearFirestore();
  }
});

test("Unauthenticated user cannot read or write user profile, favorites, history, or drafts", async () => {
  const unauthDb = testEnv.unauthenticatedContext().firestore();
  await assertFails(unauthDb.collection("users").doc(ALICE_UID).get());
  await assertFails(unauthDb.collection("users").doc(ALICE_UID).collection("favorites").get());
  await assertFails(unauthDb.collection("users").doc(ALICE_UID).collection("history").get());
  await assertFails(unauthDb.collection("users").doc(ALICE_UID).collection("drafts").get());
});

test("Authenticated owner can create and update valid UserProfile, and cross-user access is denied", async () => {
  const aliceDb = testEnv.authenticatedContext(ALICE_UID).firestore();
  const bobDb = testEnv.authenticatedContext(BOB_UID).firestore();
  const now = new Date();

  const validProfile = {
    userId: ALICE_UID,
    displayName: "Alice Engineer",
    email: "alice@example.com",
    themeMode: "SYSTEM",
    fontChoice: "JAKARTA",
    notificationsEnabled: true,
    autoCleanCache: false,
    schemaVersion: 1,
    createdAt: now,
    updatedAt: now,
  };

  await assertSucceeds(aliceDb.collection("users").doc(ALICE_UID).set(validProfile));
  await assertSucceeds(aliceDb.collection("users").doc(ALICE_UID).get());
  await assertFails(bobDb.collection("users").doc(ALICE_UID).get());

  // Shadow update with ghost field must fail
  await assertFails(
    aliceDb.collection("users").doc(ALICE_UID).update({
      isAdmin: true,
      updatedAt: new Date(),
    })
  );

  // Valid update succeeds (including compactMode)
  await assertSucceeds(
    aliceDb.collection("users").doc(ALICE_UID).update({
      themeMode: "DARK",
      fontChoice: "MONO",
      compactMode: true,
      updatedAt: new Date(),
    })
  );
});

test("Authenticated owner can manage favorites, history, and drafts with strict validation", async () => {
  const aliceDb = testEnv.authenticatedContext(ALICE_UID).firestore();
  const bobDb = testEnv.authenticatedContext(BOB_UID).firestore();
  const now = new Date();

  // Favorite
  await assertSucceeds(
    aliceDb.collection("users").doc(ALICE_UID).collection("favorites").doc("pdf_image_to_pdf").set({
      userId: ALICE_UID,
      toolId: "pdf_image_to_pdf",
      toolName: "Image to PDF",
      category: "PDF",
      createdAt: now,
    })
  );
  await assertFails(bobDb.collection("users").doc(ALICE_UID).collection("favorites").doc("pdf_image_to_pdf").get());

  // History
  await assertSucceeds(
    aliceDb.collection("users").doc(ALICE_UID).collection("history").doc("task_001").set({
      userId: ALICE_UID,
      toolId: "pdf_image_to_pdf",
      toolName: "Image to PDF",
      category: "PDF",
      status: "COMPLETED",
      inputSummary: "4 images selected",
      outputName: "Toolverse_Scan_01.pdf",
      sizeBytes: 245120,
      durationMs: 410,
      createdAt: now,
      updatedAt: now,
    })
  );
  await assertFails(
    bobDb.collection("users").doc(ALICE_UID).collection("history").doc("task_001").get()
  );

  // Draft
  await assertSucceeds(
    aliceDb.collection("users").doc(ALICE_UID).collection("drafts").doc("draft_cv_1").set({
      userId: ALICE_UID,
      draftType: "CV",
      title: "Senior Android Engineer CV",
      templateId: "ats_modern",
      structuredData: {
        fullName: "Alice Engineer",
        roleTitle: "Staff Android Developer",
      },
      createdAt: now,
      updatedAt: now,
    })
  );
  await assertFails(
    bobDb.collection("users").doc(ALICE_UID).collection("drafts").doc("draft_cv_1").get()
  );
});
