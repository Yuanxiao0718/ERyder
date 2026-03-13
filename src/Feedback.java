public class Feedback {
    private String firstName;
    private String lastName;
    private String email;
    private String completeFeedback;
    private String reviewID;
    private boolean longFeedback;

    public Feedback(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getEmail() {
        return email;
    }
    public String getCompleteFeedback() {
        return completeFeedback;
    }
    public String getReviewID() {
        return reviewID;
    }
    public boolean getLongFeedback() {
        return longFeedback;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setCompleteFeedback(String completeFeedback) {
        this.completeFeedback = completeFeedback;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setReviewID(String reviewID) {
        this.reviewID = reviewID;
    }

    public void setLongFeedback(boolean longFeedback) {
        this.longFeedback = longFeedback;
    }

    public void analyseFeedback(boolean isConcatenation,String sent1,String sent2,String sent3,String sent4,String sent5){

        if (isConcatenation) {
            this.completeFeedback =feedbackUsingConcatenation(sent1, sent2, sent3, sent4, sent5);
            this.longFeedback=checkFeedbackLength(completeFeedback);
            this.reviewID =createReviewID(firstName, lastName, completeFeedback);
        } else {
            this.completeFeedback =feedbackUsingStringBuilder(sent1, sent2, sent3, sent4, sent5);
            checkFeedbackLength(completeFeedback);
            this.reviewID =createReviewID(firstName, lastName, completeFeedback);
        }

    }
    private String feedbackUsingConcatenation(String sent1, String sent2, String sent3, String sent4, String sent5){
        String completeFeedback="";
        completeFeedback=sent1 +" " +sent2+" " +sent3+" " +sent4+" " +sent5;
        return completeFeedback;
    }
    private String feedbackUsingStringBuilder(String sent1, String sent2, String sent3, String sent4, String sent5){
        StringBuilder sb = new StringBuilder();
        sb.append(sent1);
        sb.append(sent2);
        sb.append(sent3);
        sb.append(sent4);
        sb.append(sent5);
        return sb.toString();
    }
    private boolean checkFeedbackLength(String completeFeedback) {
        if(completeFeedback.length()>500){
            longFeedback = true;
            return longFeedback;
        }else{
            longFeedback = false;
            return longFeedback;
        }
    }
    private String createReviewID(String firstName,String lastName,String completeFeedback) {
        reviewID=(firstName.substring(2,6) + lastName.substring(2,6)).toUpperCase();
        reviewID+=completeFeedback.substring(10,15).toLowerCase();
        reviewID+=completeFeedback.length()+"_";
        reviewID+=System.currentTimeMillis();
        reviewID=reviewID.replace(" ", "");
        return reviewID;
    }
    @Override
    public String toString(){
        return "Feedback from " + firstName+ " "+lastName+" {"+email+"}\n\n"+
                "Feedback: "+completeFeedback+"\n\nIf the feedback is long than 500? "+longFeedback+
                "\n\nThe review ID: "+reviewID;
    }


}
