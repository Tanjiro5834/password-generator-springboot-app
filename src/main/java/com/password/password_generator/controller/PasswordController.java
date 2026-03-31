package com.password.password_generator.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.password.password_generator.entity.Password;
import com.password.password_generator.service.PasswordService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/passwords")
@RequiredArgsConstructor
public class PasswordController {
    private final PasswordService passwordService;

    @PostMapping("/generate")
    public Password generatePassword(@RequestBody Password password) {
        return passwordService.generatePassword(password.getLength(), password.getStrength());
    }

    @GetMapping
    public List<Password> getAllPasswords(){
        return passwordService.getAllPasswords();
    }

    @PutMapping("/update")
    public Password updatePassword(@RequestBody Password password) {
        return passwordService.updatePassword(password.getId(), password.getLength(), password.getStrength());
    }

    @DeleteMapping("/delete/{id}")
    public void deletePassword(@PathVariable Long id) {
        passwordService.deletePassword(id);
    }
}
