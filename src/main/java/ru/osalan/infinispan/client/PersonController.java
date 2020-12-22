package ru.osalan.infinispan.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/getById")
    public @ResponseBody Person getById() {
        personRepository.save(new Person(1, "First", 20, "first@gmail.com"));
        personRepository.save(new Person(2, "Second", 30, "second@gmail.com"));
        personRepository.save(new Person(3, "Third", 40, "third@gmail.com"));
        personRepository.save(new Person(4, "Forth", 50, "forth@gmail.com"));
        return personRepository.findById(1).get();
    }

}
