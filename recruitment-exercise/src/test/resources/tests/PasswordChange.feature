Feature: The user can change their own password

  Rule: As a user
  I want to be able to change my own password within the guidelines of password policy
  So that the integrity of my account security requirements are met.

    Background:
      Given I am a registered user
      And I am logged in with username "user1" and password "oldPassword123"

    Scenario: Successful Password Change
      When I attempt to change my password
      And I enter "oldPassword123" as my current password
      And I enter "newSecurePassword123!" as my new password
      And I confirm my new password by entering "newSecurePassword123!" again
      Then the password change should be successful
      And I should receive a confirmation message "Your password has been changed successfully."

    Scenario: Failed Password Change with Incorrect Current Password
      When I attempt to change my password
      And I enter "incorrectOldPassword" as my current password
      And I enter "newSecurePassword123!" as my new password
      And I confirm my new password by entering "newSecurePassword123!" again
      Then the password change should fail
      And I should receive an error message "Your current password is incorrect."

    Scenario: Failed Password Change with Non-Matching New Passwords
      When I attempt to change my password
      And I enter "oldPassword123" as my current password
      And I enter "newSecurePassword123!" as my new password
      And I confirm my new password by entering "newSecurePassword456!" again
      Then the password change should fail
      And I should receive an error message "The new passwords do not match."

    Scenario: Failed Password Change with Weak New Password
      When I attempt to change my password
      And I enter "oldPassword123" as my current password
      And I enter "weak" as my new password
      And I confirm my new password by entering "weak" again
      Then the password change should fail
      And I should receive an error message "Your new password does not meet the security requirements."

    Scenario: Failed Password Change without Providing Current Password
      When I attempt to change my password
      And I enter "" as my current password
      And I enter "newSecurePassword123!" as my new password
      And I confirm my new password by entering "newSecurePassword123!" again
      Then the password change should fail
      And I should receive an error message "Please enter your current password."

    Scenario: Failed Password Change with Same New and Old Passwords
      When I attempt to change my password
      And I enter "oldPassword123" as my current password
      And I enter "oldPassword123" as my new password
      And I confirm my new password by entering "oldPassword123" again
      Then the password change should fail
      And I should receive an error message "Your new password cannot be the same as your old password."
