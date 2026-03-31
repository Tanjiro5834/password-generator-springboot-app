package com.password.password_generator.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.password.password_generator.entity.Password;
import com.password.password_generator.repository.PasswordRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PasswordService {
    private final PasswordRepository passwordRepository;

    public List<Password> getAllPasswords() {
        return passwordRepository.findAll();
    }

    public Password generatePassword(int length, String strength){
        String upperCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String specialCharacters = "!@#$%^&*()-_=+[]{}|;:,.<>?";

        String characterSet = "";

        switch (strength.toLowerCase()) {
            case "weak":
                characterSet = lowerCaseLetters;
                break;
            case "medium":
                characterSet = lowerCaseLetters + upperCaseLetters;
                break;
            case "strong":
                characterSet = lowerCaseLetters + upperCaseLetters + numbers + specialCharacters;
                break;
            default:
                throw new IllegalArgumentException("Invalid strength level. Choose 'weak', 'medium', or 'strong'.");
        }

        StringBuilder generatedPassword = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int randomIndex = (int) (Math.random() * characterSet.length());
            generatedPassword.append(characterSet.charAt(randomIndex));
        }

        Password passwordEntity = new Password();
        passwordEntity.setGeneratedPassword(generatedPassword.toString());
        passwordEntity.setLength(length);
        passwordEntity.setStrength(strength);

        return passwordRepository.save(passwordEntity);
    }

    public void deletePassword(Long id) {
        passwordRepository.deleteById(id);
    }

    public Password getPasswordById(Long id){
        return passwordRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Password not found with id: " + id));
    }

    @Transactional
    public Password updatePassword(Long id, int length, String strength) {
        Password existingPassword = getPasswordById(id);
        Password updatedPassword = generatePassword(length, strength);
        existingPassword.setGeneratedPassword(updatedPassword.getGeneratedPassword());
        existingPassword.setLength(length);
        existingPassword.setStrength(strength);
        return passwordRepository.save(existingPassword);
    }
}
