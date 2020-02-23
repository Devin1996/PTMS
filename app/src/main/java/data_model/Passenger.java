package data_model;

public class Passenger {

        private String pasRegName;
        private String pasRegEmail;
        private String pasRegPwd;
        private String pasRegCpwd;

    public Passenger(String s, String grace_hopper) {
    }

    public Passenger(String pasRegName, String pasRegEmail, String pasRegPwd, String pasRegCpwd) {
        this.pasRegName = pasRegName;
        this.pasRegEmail = pasRegEmail;
        this.pasRegPwd = pasRegPwd;
        this.pasRegCpwd = pasRegCpwd;
    }

    public String getPasRegName() {
        return pasRegName;
    }

    public void setPasRegName(String pasRegName) {
        this.pasRegName = pasRegName;
    }

    public String getPasRegEmail() {
        return pasRegEmail;
    }

    public void setPasRegEmail(String pasRegEmail) {
        this.pasRegEmail = pasRegEmail;
    }

    public String getPasRegPwd() {
        return pasRegPwd;
    }

    public void setPasRegPwd(String pasRegPwd) {
        this.pasRegPwd = pasRegPwd;
    }

    public String getPasRegCpwd() {
        return pasRegCpwd;
    }

    public void setPasRegCpwd(String pasRegCpwd) {
        this.pasRegCpwd = pasRegCpwd;
    }
}
