package com.solovev.usersfx.util;

import com.solovev.usersfx.model.User;

public class UserDecoratorClass extends User {
    /**
     * Shows id and name of the user
     * @param user to decorate
     * @return user info string representation
     */
    public static String shortUserInfo(User user) {
        return String.format("id: %d, Name: %s", user.getId(), user.getName());
    }
    /**
     * Method creates nice String representation of the User:
     * @param user to decorate     *
     * @return with long user info representation
     * "
     */
    public static String longUserInfo(User user) {
        int lvlCount = 0;
        String userToString = user.toString();
        String filtered = userToString.substring(0, userToString.length() - 1)
                .replaceFirst("User\\{", "")
                .replaceAll(" ", "");
        StringBuilder sb = new StringBuilder();
        for (char c : filtered.toCharArray()) {
            sb.append(c);
            if (c == ',') {
                sb.append("\n").append("\t".repeat(lvlCount));
            }
            if (c == '{') {
                sb.append("\n").append("\t".repeat(++lvlCount));
            }
            if (c == '}') {
                lvlCount--;
            }
        }
        return sb.toString();
    }

}
