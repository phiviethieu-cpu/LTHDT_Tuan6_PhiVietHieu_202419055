package nguoimuon;

public class TeachingAssistant implements IBorrower {
    private final String borrowerId;
    private final String fullName;

    public TeachingAssistant(String borrowerId, String fullName) {
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
        return 2;
    }

    @Override
    public String toString() {
        return "Tro giang " + fullName + " (" + borrowerId + ")";
    }
}
