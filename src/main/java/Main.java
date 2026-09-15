import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<User> users = DataManager.loadUsers();

    public static void main(String[] args) {
        Database.initialize();
        while (true) {
            System.out.println("\n======================================");
            System.out.println("       DIGITAL WALLET SYSTEM");
            System.out.println("======================================");
            System.out.println("1. Register"); System.out.println("2. Login"); System.out.println("3. Exit");
            switch (readInt("Enter choice: ")) {
                case 1 -> register(); case 2 -> login();
                case 3 -> { DataManager.saveUsers(users); System.out.println("Thank you for using Digital Wallet System."); scanner.close(); return; }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void register() {
        System.out.println("\n========== REGISTER ==========");
        String name=readName("Enter full name: "), email=readEmail("Enter email: ");
        String phone=readPhone("Enter phone number: "), pin=readPin("Create a 4-digit PIN: ");
        for (User u: users) {
            if (u.getEmail().equalsIgnoreCase(email)) { System.out.println("An account with this email already exists."); return; }
            if (u.getPhone().equals(phone)) { System.out.println("An account with this phone number already exists."); return; }
        }
        users.add(new User(name,email,phone,pin)); Database.saveUser(name,email); DataManager.saveUsers(users);
        System.out.println("Registration successful.");
    }

    private static void login() {
        System.out.println("\n========== LOGIN ==========");
        String email=readText("Enter email: "), pin=readPin("Enter PIN: ");
        for (User u: users) if (u.getEmail().equalsIgnoreCase(email) && u.getPin().equals(pin)) {
            System.out.println("\nLogin successful. Welcome, "+u.getName()+"!"); walletMenu(u); return;
        }
        System.out.println("Invalid email or PIN.");
    }

    private static void walletMenu(User user) {
        WalletService service=user.getWalletService();
        while (true) {
            System.out.println("\n========== WALLET MENU ==========");
            String[] menu={"1. Create Wallet","2. Check Balance","3. Add Money","4. Withdraw Money","5. Send Money",
                    "6. Beneficiaries","7. Pay Bill","8. Transaction History","9. Export Statement","10. Logout"};
            for (String s:menu) System.out.println(s);
            switch (readInt("Enter choice: ")) {
                case 1 -> {
                    if (service!=null) System.out.println("Wallet already exists.");
                    else { user.createWallet(); service=user.getWalletService(); DataManager.saveUsers(users); System.out.println("Wallet created successfully."); }
                }
                case 2 -> { if (!ready(service)) break; System.out.println("\nCurrent balance: Rs."+service.getWallet().getBalance()); }
                case 3 -> { if (!ready(service)) break; addMoney(service); DataManager.saveUsers(users); }
                case 4 -> { if (!ready(service)) break; withdrawMoney(service); DataManager.saveUsers(users); }
                case 5 -> { if (!ready(service)) break; sendMoney(user,service); DataManager.saveUsers(users); }
                case 6 -> { if (!ready(service)) break; beneficiaryMenu(service); DataManager.saveUsers(users); }
                case 7 -> { if (!ready(service)) break; payBill(service); DataManager.saveUsers(users); }
                case 8 -> { if (!ready(service)) break; showTransactions(service); }
                case 9 -> { if (!ready(service)) break; exportStatement(user,service); }
                case 10 -> { DataManager.saveUsers(users); System.out.println("Logged out successfully."); return; }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static boolean ready(WalletService s) {
        if (s!=null) return true;
        System.out.println("Please create a wallet first."); return false;
    }

    private static void addMoney(WalletService s) {
        try { s.addMoney(readAmount("Enter amount to add: ")); System.out.println("Money added successfully."); }
        catch (WalletException e) { System.out.println("Error: "+e.getMessage()); }
    }

    private static void withdrawMoney(WalletService s) {
        try { s.withdrawMoney(readAmount("Enter amount to withdraw: ")); System.out.println("Money withdrawn successfully."); }
        catch (WalletException e) { System.out.println("Error: "+e.getMessage()); }
    }

    private static void sendMoney(User sender, WalletService s) {
        System.out.println("\n========== SEND MONEY ==========");
        User receiver=findUser(readEmail("Enter receiver email: "));
        if (receiver==null) { System.out.println("Receiver not found."); return; }
        if (receiver==sender) { System.out.println("You cannot send money to yourself."); return; }
        WalletService rs=receiver.getWalletService();
        if (rs==null) { System.out.println("Receiver does not have a wallet."); return; }
        try { s.sendMoney(rs,readAmount("Enter amount to send: ")); System.out.println("Money sent successfully to "+receiver.getName()+"."); }
        catch (WalletException e) { System.out.println("Error: "+e.getMessage()); }
    }

    private static void beneficiaryMenu(WalletService s) {
        System.out.println("\n========== BENEFICIARIES ==========");
        System.out.println("1. Add Beneficiary"); System.out.println("2. View Beneficiaries");
        System.out.println("3. Remove Beneficiary"); System.out.println("4. Back");
        switch (readInt("Enter choice: ")) {
            case 1 -> { s.addBeneficiary(new Beneficiary(readName("Enter beneficiary name: "),readEmail("Enter beneficiary email: "))); System.out.println("Beneficiary added successfully."); }
            case 2 -> showBeneficiaries(s.getBeneficiaries());
            case 3 -> removeBeneficiary(s);
            case 4 -> { }
            default -> System.out.println("Invalid choice.");
        }
    }

    private static void showBeneficiaries(List<Beneficiary> list) {
        if (list.isEmpty()) { System.out.println("No beneficiaries found."); return; }
        System.out.println("\n------ Saved Beneficiaries ------");
        for (int i=0;i<list.size();i++) System.out.println((i+1)+". "+list.get(i));
    }

    private static void removeBeneficiary(WalletService s) {
        List<Beneficiary> list=s.getBeneficiaries();
        if (list.isEmpty()) { System.out.println("No beneficiaries found."); return; }
        for (int i=0;i<list.size();i++) System.out.println((i+1)+". "+list.get(i));
        try { s.removeBeneficiary(readInt("Enter beneficiary number to remove: ")-1); System.out.println("Beneficiary removed successfully."); }
        catch (WalletException e) { System.out.println("Error: "+e.getMessage()); }
    }

    private static void payBill(WalletService s) {
        System.out.println("\n========== BILL PAYMENT ==========");
        System.out.println("1. Electricity"); System.out.println("2. Water"); System.out.println("3. Mobile");
        System.out.println("4. Internet"); System.out.println("5. Gas");
        String type=switch(readInt("Choose bill type: ")){case 1->"Electricity";case 2->"Water";case 3->"Mobile";case 4->"Internet";case 5->"Gas";default->null;};
        if (type==null) { System.out.println("Invalid bill type."); return; }
        String number=readText("Enter consumer number: "); BigDecimal amount=readAmount("Enter bill amount: ");
        try { s.payBill(type,number,amount); System.out.println(type+" bill paid successfully."); }
        catch (WalletException e) { System.out.println("Error: "+e.getMessage()); }
    }

    private static void showTransactions(WalletService s) {
        System.out.println("\n========== TRANSACTION HISTORY ==========");
        List<Transaction> list=s.getTransactions();
        if (list.isEmpty()) { System.out.println("No transactions found."); return; }
        for (Transaction t:list) System.out.println(t);
    }

    private static void exportStatement(User u, WalletService s) {
        System.out.println("\n========== EXPORT STATEMENT ==========");
        if (s.getTransactions().isEmpty()) { System.out.println("No transactions available."); return; }
        try { s.exportStatement(u.getName()); System.out.println("Statement exported successfully."); }
        catch (WalletException e) { System.out.println("Error: "+e.getMessage()); }
    }

    private static User findUser(String email) {
        for (User u:users) if (u.getEmail().equalsIgnoreCase(email)) return u;
        return null;
    }

    private static int readInt(String message) {
        while (true) try { System.out.print(message); return Integer.parseInt(scanner.nextLine().trim()); }
        catch (NumberFormatException e) { System.out.println("Please enter a valid number."); }
    }

    private static String readText(String message) { System.out.print(message); return scanner.nextLine().trim(); }

    private static String readName(String message) {
        while (true) {
            String name=readText(message);
            if (name.length()>=2 && name.matches("[A-Za-z ]+")) return name;
            System.out.println("Name must contain only letters and spaces.");
        }
    }

    private static String readEmail(String message) {
        while (true) {
            String email=readText(message);
            if (email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) return email;
            System.out.println("Please enter a valid email address.");
        }
    }

    private static String readPhone(String message) {
        while (true) { String p=readText(message); if (p.matches("[6-9]\\d{9}")) return p; System.out.println("Phone number must be a valid 10-digit mobile number."); }
    }

    private static String readPin(String message) {
        while (true) { String p=readText(message); if (p.matches("\\d{4}")) return p; System.out.println("PIN must contain exactly 4 digits."); }
    }

    private static BigDecimal readAmount(String message) {
        while (true) try {
            System.out.print(message); BigDecimal a=new BigDecimal(scanner.nextLine().trim());
            if (a.compareTo(BigDecimal.ZERO)<=0) { System.out.println("Amount must be greater than zero."); continue; }
            if (a.scale()>2) { System.out.println("Amount cannot have more than two decimal places."); continue; }
            return a;
        } catch (NumberFormatException e) { System.out.println("Please enter a valid amount."); }
    }
}