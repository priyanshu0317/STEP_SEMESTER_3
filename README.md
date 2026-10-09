# STEP_SEMESTER_3

## Date: 10-10-2026 (Session 5)

**Today's Work:**
- Implemented and verified all 10 Java problems for Session 5 (Week 5) under the `access_modifiers_and_encapsulation` package topic.
- Live-Coding / Practice Problems:
  - Movie Ticket Field Visibility Checker classifying access across same class, package, and different packages with batch summaries (`MovieTicketAccessChecker.java`).
  - Subclass Ticket Access implementing protected access across packages with own-type vs parent-type references (`SubclassTicketAccess.java`).
  - Seat Booking Encapsulation Guard enforcing constructor validation and silent boundary rejections on state transitions (`CineScreen.java`).
  - MovieBookingProfile JavaBean with constructor chaining and write-only OTP property (`MovieBookingProfile.java`).
  - Immutable Booking Receipt and nightly batch settlement with defensive array copying and wither pattern (`BookingReceiptSettlement.java`).
- Assignment Problems:
  - Membership Field Reach Checker grouping access classification by modifier (`MembershipFieldReachChecker.java`).
  - Reference Desk Subclass Reach identifying the first denied access attempt in ordered streams (`ReferenceDeskSubclassReach.java`).
  - Book Copy Circulation Guard protecting circulation counters with validated method boundaries (`BookCopyCirculationGuard.java`).
  - LibraryMember JavaBean with write-once membershipId and write-only security answer (`LibraryMember.java`).
  - Immutable Loan Receipt & Nightly Circulation Ledger handling defensive copies, wither methods, and null-tolerant polymorphic dispatch (`LoanReceiptLedger.java`).
- Created and pushed `feature/session_5` containing all tested Java source files.

**Next Session Plan:**
- Proceed with Session 6 problems following the established course workflow.

**Issues Faced:**
- None. All 10 programs compiled and passed runtime verification against all problem statements and sample test cases.

## Date: 10-10-2026 (Session 4)

**Today's Work:**
- Implemented and verified all 10 Java problems for Session 4 (Week 4) under the `constructors_and_keywords` package topic.
- Live-Coding / Practice Problems:
  - Library Book Cataloguing with `this(...)` constructor chaining and default pending status (`LibraryBook.java`).
  - Payroll Batch Bonus Round using `this` to resolve field/parameter clashes during batch salary increments (`Employee.java`).
  - Late Fees Calculator enforcing `final` method locking on formulas and skipping on-time accounts (`Account.java`).
  - One-Time College Setup with static initialization blocks and batch student object creation (`SrmStudent.java`).
  - Account Batch Payments dispatching polymorphic accounts using `instanceof` and tracking batch statistics (`AccountBatchPayments.java`).
- Assignment Problems:
  - Hackathon Registration with overloaded constructors for solo and team participants via `this(...)` chaining (`Participant.java`).
  - Canteen Inventory Batch Restock using `this` to update inventory quantities (`Item.java`).
  - Parking Overstay Fine Calculator with `final` fine computation methods and on-time validation (`ParkingTicket.java`).
  - Library Membership Card Setup using static initialization blocks executed once for batch issuance (`MembershipCard.java`).
  - Canteen Closing-Time Payment Dispatch using `instanceof` type checking for credit card fee calculations and running totals (`CanteenPaymentDispatch.java`).
- Created and pushed `feature/session_4` containing all tested Java source files.

**Next Session Plan:**
- Proceed with Session 5 problems following the established course workflow.

**Issues Faced:**
- None. All programs compiled and passed runtime verification with sample inputs and boundary cases.

## Date: 10-10-2026 (Session 3)

**Today's Work:**
- Implemented and verified all 10 Java problems for Session 3 (Week 3) under the `object_oriented_programming` package topic.
- Live-Coding / Practice Problems:
  - Transition from parallel arrays to OOP `PlacementRecord` class with constructor, array of objects, and formatted output (`PlacementRecord.java`).
  - Encapsulated `MessWallet` with private balance, opening balance validation, topUp, deduct validation, and read-only balance getter (`MessWallet.java`).
  - Overloaded constructors for `Course` using `this(...)` constructor chaining and total credits calculation (`Course.java`).
  - Reference copying and object identity check with `IdCard` demonstrating `==` reference comparison vs modified state (`IdCard.java`).
  - Instance vs static members with `Student` class tracking shared `collegeName` and incrementing `studentCount` accessed statically (`Student.java`).
- Assignment Problems:
  - Transition from parallel arrays to OOP `BookInventory` class with constructor and instance method (`BookInventory.java`).
  - Encapsulated `PayrollAccount` with private salary and bonus, bonus crediting, percentage tax deduction, and net salary getter (`PayrollAccount.java`).
  - Overloaded constructors for `Employee` handling permanent employees and interns via `this(...)` chaining (`Employee.java`).
  - Reference copying with `HallTicket` demonstrating shared reference modification vs separate instance comparison (`HallTicket.java`).
  - Static vs instance design with `CompanyEmployee` sharing static company name and employee count across objects (`CompanyEmployee.java`).
- Created and pushed `feature/session_3` containing all tested Java source files.

**Next Session Plan:**
- Proceed with Session 4 problems following the established course workflow.

**Issues Faced:**
- Resolved potential class name collision between Assignment M3 and M5 by uniquely naming M5 as `CompanyEmployee` while preserving package integrity.

## Date: 09-10-2026 (Session 2)

**Today's Work:**
- Implemented and verified all 10 Java problems for Session 2 (Week 2) under the `string` package topic.
- Live-Coding / Class Problems:
  - Vowel & Consonant Counter with case-insensitive character evaluation (`VowelConsonantCounter.java`).
  - CSV Student Record Parser validating field counts and formatting student details (`CsvStudentRecordParser.java`).
  - File Extension Validator checking permitted extensions (`FileExtensionValidator.java`).
  - Masked Phone Number Formatter validating 10 digits and masking the first 6 digits (`MaskedPhoneNumberFormatter.java`).
  - Bank Transaction Reference Generator & Validator normalizing bank codes and validating reference structures (`BankTransactionReferenceValidator.java`).
- Assignment Problems:
  - ATM PIN Length Validator checking 4-digit requirement (`AtmPinLengthValidator.java`).
  - Word Reversal Encoder reversing individual words in a sentence (`WordReversalEncoder.java`).
  - Product Inventory CSV Parser validating product fields and formatted output (`ProductInventoryCsvParser.java`).
  - Library ISBN Normalizer & Validator normalizing publisher codes and validating length and character types (`LibraryIsbnValidator.java`).
  - Stop-Word-Filtered Word Frequency Report excluding filler words and sorting word frequency in descending order (`StopWordFrequencyReport.java`).
- Created and pushed `feature/session_2` containing all tested Java source files.

**Next Session Plan:**
- Proceed with Session 3 problems following the established course workflow.

**Issues Faced:**
- None. All programs compiled and passed runtime verification with sample and edge cases.

## Date: 09-10-2026

**Today's Work:**
- Implemented and verified all 10 Java problems for Session 1 (Week 1) under the `arrays_and_strings` package topic.
- Live-Coding / Class Problems:
  - Rock-Paper-Scissors Game simulator with 5 rounds, summary table, and win percentage calculation (`RockPaperScissors.java`).
  - Palindrome Checker comparing Iterative, Recursive, and Array-Reversal approaches (`PalindromeChecker.java`).
  - BMI Calculator for a team with health status classification and tabular wellness report (`BmiCalculator.java`).
  - First Non-Repeating Character finder using frequency counting (`FirstNonRepeatingCharacter.java`).
  - Reverse Customer Name preserving original data (`ReverseCustomerName.java`).
- Assignment Problems:
  - Exam Hall Seat Duplication Checker using nested loops without Collections (`SeatDuplicationChecker.java`).
  - Typing Speed Test Accuracy Checker with character matching percentage and first mismatch reporting (`TypingAccuracyChecker.java`).
  - Traffic Signal Streak Analyzer tracking longest consecutive color streak (`TrafficSignalStreakAnalyzer.java`).
  - Warehouse Inventory Balancer computing totals, balance status, and highest quantity location (`WarehouseInventoryBalancer.java`).
  - Movie Review Word Length Profiler categorizing Short, Medium, and Long words (`MovieReviewWordLengthProfiler.java`).
- Initialized clean Java project skeleton on `develop` branch.
- Created and pushed `feature/session_1` containing all tested Java source files.

**Next Session Plan:**
- Proceed with Session 2 problems (Practice Problems and Assignments) following the established Git workflow.

**Issues Faced:**
- None. Initial network connection timeout while pushing upstream was safely resolved with a clean retry.