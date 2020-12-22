package ru.osalan.infinispan.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.keyvalue.annotation.KeySpace;

import java.io.Serializable;

@KeySpace("person")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;

    private String fullName;

    private Integer age;

    private String email;

}
