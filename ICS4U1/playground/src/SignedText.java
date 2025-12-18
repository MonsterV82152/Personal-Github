public class SignedText {
    private String firstName;
    private String lastName;

    public SignedText(String first, String last) {
        this.firstName = first;
        this.lastName = last;
    }

    public String getSignature() {
        if (firstName.equals("")) {
            return lastName;
        } else {
            return firstName.substring(0, 1) + "-" + lastName;
        }
    }

    public String addSignature(String text) {
        String signature = this.getSignature();
        int textLength = text.length(), signatureLength = signature.length();

        if (textLength >= signatureLength) {
            if (text.substring(0, signatureLength).equals(signature)) {
                return text.substring(signatureLength, textLength) + signature;
            } else if (text.substring(textLength - signatureLength, textLength).equals(signature)) {
                return text;
            }
        }
        return text + signature;
    }
}