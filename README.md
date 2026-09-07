# Email Administration Application

A console-based Java application for IT administrators to create and manage corporate email accounts for new employees.  
Generates email addresses in the format `firstname.lastname@department.company.com`, assigns random passwords, and allows updates to mailbox capacity and alternate email addresses. Data is persisted between runs using Java serialization.

---

## Features

- **Create new employee email accounts**  
  - Input first name, last name, and select a department (Sales, Development, Accounting, HR).  
  - Automatically generates:  
    - Email: `firstname.lastname@department.company.com` (lowercased)  
    - Random 8‑character password (alphanumeric)  
  - Prevents duplicate accounts (based on name + lastname + department, case‑sensitive – see note below).

- **View all created emails** – display a list of all stored accounts with passwords and email addresses.

- **Change mailbox capacity** – update the storage quota (default 100) for a specific user.

- **Set alternate email address** – assign a secondary email to any existing account.

- **Persistent storage** – all data is automatically saved to `emails.mails` on exit and reloaded on startup.

- **Interactive menu** – easy‑to‑use console interface.

---

## Project Structure

```
.
├── Main.java              – Console menu and user interaction
├── User.java              – Data model (first name, last name, department) with equals/hashCode
├── Email.java             – Email model (password, address, capacity, alternate email)
├── Departments.java       – Enum of available departments
├── EmailsService.java     – Business logic, in‑memory storage (Map<User, Email>), persistence
├── emails.mails           – Serialized data file (auto‑generated)
└── README.md
```

---

## Usage

After launching, you'll see the main menu:

```
______________
CHOICE ACTION:
______________
1. ADD a NEW USER
2. VIEW LIST of emails
3. CHANGE mailbox CAPACITY (default=100)
4. SET ALT EMAIL
5. EXIT
```

- **Option 1** – enter first name, last name, and choose a department from the list. The system will generate and store the email account.
- **Option 2** – displays all stored emails (password and email address).
- **Option 3** – enter the user's credentials (name, last name, department), then specify a new mailbox capacity (integer).
- **Option 4** – enter the user's credentials, then provide an alternate email address (string).
- **Option 5** – saves data and exits.

All changes are saved automatically when you choose **Exit**.

---

## Example Session

```
______________
CHOICE ACTION:
______________
1. ADD a NEW USER
2. VIEW LIST of emails
3. CHANGE mailbox CAPACITY (default=100)
4. SET ALT EMAIL
5. EXIT
Choose: 1

Enter your name: John
Enter your lastname: Doe
Select your department:
1. SALES
2. DEVELOPMENT
3. ACCOUNTING
4. HR
Choose department: 1

(Email created silently)

Choose: 2
password='OgaZCixF', email='john.doe@sales.company.com'
```

---

## Persistence

- The `EmailsService` uses `ObjectOutputStream`/`ObjectInputStream` to serialize the `HashMap<User, Email>`.
- The file `emails.mails` is stored in the working directory.
- If the file exists, data is loaded automatically when the program starts.
- If the file is missing, an empty map is created.

---

## Important Notes on Uniqueness

- **User identification** – the system uses **all three fields** (first name, last name, department) to determine uniqueness via `equals()` and `hashCode()` in the `User` class.
- **Case sensitivity** – comparisons are case‑sensitive.  
  For example, `"john"` and `"John"` are treated as different users.  
  *Tip:* Enter names consistently (e.g., all lowercase) to avoid accidental duplicates.

- **Duplicate prevention** – if you attempt to add a user that already exists (identical fields), the existing entry will be **replaced** (the old email is overwritten). The system does not display a warning in the current version; future improvements may add explicit feedback.

---

## Design Highlights

- **Separation of concerns** –  
  - `User` & `Email` – pure data models.  
  - `EmailsService` – manages the collection, business rules, and persistence.  
  - `Main` – handles console I/O and user interaction.
- **Enum for departments** – ensures valid department selection.
- **Built‑in password generator** – random alphanumeric strings of length 8.
- **Serialization** – simple and effective for small‑scale data storage.

---

## License

This project is for educational purposes. Free to use and modify as needed.
