

package net.studworld.packets;

/**
 * Class p2_AuthUser  with necessary fields.Data packet for transactions with id = 2.
 * @extends Packet.
 * @author Ярослав Кузнецов
 */
public class p2_AuthUser extends Packet {
    private String phone, email, pass;

    /**
     *
     */
    public p2_AuthUser() {
        this.setId(2);
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the pass
     */
    public String getPass() {
        return pass;
    }

    /**
     * @param pass the pass to set
     */
    public void setPass(String pass) {
        this.pass = pass;
    }
}
