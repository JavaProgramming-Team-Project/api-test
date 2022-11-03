package auth;

import entity.Member;

public class AuthMember {
    Member member;

    public AuthMember(Member member) {
        this.member = member;
    }

    public Member getMember() {
        return member;
    }
}
