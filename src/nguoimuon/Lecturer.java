package nguoimuon;

public class Lecturer implements IBorrower {
    private final String borrowerId;
    private final String fullName;

    public Lecturer(String borrowerId, String fullName) {
        this.borrowerId = borrowerId;
        this.fullName = fullName;
    }

    @Override
    public String getBorrowerId() {
        return borrowerId;
    }

    @Override
    public String getFullName() {
        return fullName;
    }

    @Override
    public int getMaximumDevices() {
        return 3;
    }

    @Override
    public String toString() {
        return "Giang vien " + fullName + " (" + borrowerId + ")";
    }
}
