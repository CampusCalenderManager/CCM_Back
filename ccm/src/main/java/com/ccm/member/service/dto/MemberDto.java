/*
 * Created by VertexToEdge on 2022/5/14.
 */

package com.ccm.member.service.dto;

import java.io.Serializable;

import com.ccm.member.domain.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberDto{
    private String username;
    private String name;

    @Builder
    public MemberDto(String username, String name) {
        this.username = username;
        this.name = name;
    }

    public static MemberDto from(Member member) {
        return MemberDto.builder()
                .username(member.getUsername())
                .name(member.getName())
                .build();
    }

    public Member toEntity() {
        return Member.builder()
                .username(username)
                .name(name)
                .build();
    }
}
