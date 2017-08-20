package com.sekapato.entity;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

public class MailForm {

    public MailForm() {

    }

    @NotBlank(message = "お名前を入力して下さい")
    public String name;

    @NotBlank(message = "メールアドレスを入力して下さい")
    @Email(message = "メールアドレスの形式が不正です")
    public String mailAddress;

    @NotBlank(message = "メールアドレス(確認用)を入力して下さい")
//    @Column(nullable = true)
//    @Phone(onlyNumber = true)
    public String mailAddressConfirm;

    @NotBlank(message = "お問い合わせ内容を入力して下さい")
    public String message;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public String getMailAddressConfirm() {
        return mailAddressConfirm;
    }

    public void setMailAddressConfirm(String mailAddressConfirm) {
        this.mailAddressConfirm = mailAddressConfirm;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "MailForm [name=" + name + ", mailAddress=" + mailAddress + ", mailAddressConfirm=" + mailAddressConfirm
                + ", message=" + message + "]";
    }

}
