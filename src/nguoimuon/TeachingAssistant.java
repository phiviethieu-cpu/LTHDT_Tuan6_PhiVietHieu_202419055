/****************/
// Ma sinh vien: 202419055
// Ho ten: Phi Viet Hieu
/****************/
package nguoimuon;

public class TeachingAssistant implements IBorrower {
    private final String borrowerId;
    private final String fullName;

    public TeachingAssistant(String borrowerId, String fullName) {
        if (borrowerId == null || borrowerId.trim().isEmpty())
            throw new IllegalArgumentException("Ma nguoi muon khong duoc rong");
        if (fullName == null || fullName.trim().isEmpty())
            throw new IllegalArgumentException("Ho ten nguoi muon khong duoc rong");
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
