package com.scm.helpers;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Message {
    public String content;
//    default messageType
    @Builder.Default
    public MessageType type = MessageType.blue;

}
