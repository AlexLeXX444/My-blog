package com.app.blog.services;

import com.app.blog.models.User;
import com.app.blog.repositorys.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    /*
    Описание паттерна логина пользователя:
        [a-zA-Z] должен начинаться со строчной или заглавной буквы латиницы;
        [a-zA-Z0-9_-] состоит из букв латиницы в нижнем и верхнем регистре, также включает цифры и спец сиволы тире и нижнего подчеркивания;
        {6,19} имеет длинну от 6 до 20 символов без пробелов и табуляции.
     */
    private final String patternForLogin = "^[a-zA-Z][a-zA-Z0-9_-]{5,19}$";
    /*
    Описание паттерна пароля пользователя:
        (?=.*[0-9]) должен иметь хотя бы одну цыфру;
        (?=.*[a-z]) должен иметь одну строчную букву латиницы;
        (?=.*[A-Z]) должен иметь одну заглавную букву латиницы;
        (?=.*[@#$%^&+=]) должен иметь один из спец символов;
        (?=\\S+$) никаких пробелов и табуляции;
        {8,25} длинна от 8 до 25 символов.
    */
    private final String patternForPassword = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,20}$";

    /*
    Описания паттерна EMail-адреса пользователя:
    Ограничения для локальной часть (до знака @):
        {1,64} длинна не более 64 символов;
        [A-Za-z0-9] начинается с латинского символа в нижнем или вехнем регистре или с цифры;
        [A-Za-z0-9_-] может содержать символы латинского алфавита в нижнем или верхнем регистре и спец символы тире и нижнего подчеркивания;
        так же допускиется единсвенный спецсимвол точка, как разделитель (никаких последоватеьных точек).

    Ограничения для доменной части (после знака @):
        [A-Za-z0-9-] состоит из латинских символов в верхнем или нижнем регистре, цифр и может включать спец символ тире;
        [A-Za-z] домен (после знака точка) может состоять только из латинских символов в верхнем или нижнем регистре;
        {2,64} длинна не менее 2 и не более 64 символов.
    */
    private final String patternForEMail = "\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*\\.\\w{2,4}";

    public int registerNew(User user) {
        System.out.println(user.toString());
        if (user.getLogin().length() != 0 && user.getPassword().length() != 0 && user.getEmail().length() != 0) {

            if (validatePattern(user.getLogin(), this.patternForLogin)) {
                return 1;
            }
            if (validatePattern(user.getPassword(), this.patternForPassword)) {
                return 2;
            }
            if (validatePattern(user.getEmail(), this.patternForEMail)) {
                return 3;
            }
            userRepository.save(user);
            return 0;
        }
        return 4;
    }

    private boolean validatePattern(String stringForMatch, String patternForMatch) {
        final Pattern pattern = Pattern.compile(patternForMatch);
        Matcher matcher = pattern.matcher(stringForMatch);
        return !matcher.matches();
    }
}
