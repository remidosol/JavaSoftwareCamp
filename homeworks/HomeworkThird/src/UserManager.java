public class UserManager {
    public void updateAccount(User user, int id, String firstName, String lastName,
                              String email, String avatarUrl){
        user.setId(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setAvatarUrl(avatarUrl);
        user.setEmail(email);
        System.out.println(user.getFirstName() + " your account has been updated.");
    }

    public void deleteAccount(User user){
        System.out.println(user.getFirstName() + ", your account has been deleted.");
    }
}
