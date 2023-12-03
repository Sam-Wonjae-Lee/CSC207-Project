package view;

import data_access.SpotifyApiCallGetInfoDataAccessObject;
import entity.User;
import interface_adapter.home_page.HomePageController;
import interface_adapter.home_page.HomePageViewModel;
import interface_adapter.match.MatchController;
import interface_adapter.match.MatchState;
import interface_adapter.match.MatchViewModel;
import interface_adapter.send_invite.SendInviteController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class MatchView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "Matches";

    private final HomePageViewModel homePageViewModel;
    private final HomePageController homePageController;
    private final MatchViewModel matchViewModel;
    private final MatchController matchController;
    private final SendInviteController sendInviteController;
    private SpotifyApiCallGetInfoDataAccessObject spotifyAPI;

//  Buttons
    private final JButton Back;
    private final JButton Invite_1;
    private final JButton Invite_2;
    private final JButton Invite_3;

//  Labels
    private final JLabel UserName_1;
    private final JLabel UserName_2;
    private final JLabel UserName_3;


    public MatchView(MatchViewModel matchViewModel, MatchController matchController, HomePageViewModel homePageViewModel, HomePageController homePageController, SendInviteController sendInviteController) {
        this.matchViewModel = matchViewModel;
        this.matchController = matchController;
        this.homePageViewModel = homePageViewModel;
        this.homePageController = homePageController;
        this.sendInviteController = sendInviteController;

//      Makes matchViewModel a listener
        matchViewModel.addPropertyChangeListener(this);

        // This is a collection of buttons and labels with GridLayout
        JPanel buttonsPanel = new JPanel(new GridLayout(0, 2));

// Invite Buttons
        Invite_1 = new JButton(MatchViewModel.INVITE_BUTTON_LABEL_1);
        Invite_2 = new JButton(MatchViewModel.INVITE_BUTTON_LABEL_2);
        Invite_3 = new JButton(MatchViewModel.INVITE_BUTTON_LABEL_3);

// UserNames
//        UserName_1 = new JLabel(matchViewModel.getState().getUSERNAMES().get(0));
//        UserName_2 = new JLabel(matchViewModel.getState().getUSERNAMES().get(1));
//        UserName_3 = new JLabel(matchViewModel.getState().getUSERNAMES().get(0));
        //TODO: fix this or die
        UserName_1 = new JLabel("user1");
        UserName_2 = new JLabel("user2");
        UserName_3 = new JLabel("user3");

// Add UserNames and Invite buttons to the buttons panel
        buttonsPanel.add(UserName_1);
        buttonsPanel.add(Invite_1);
        buttonsPanel.add(UserName_2);
        buttonsPanel.add(Invite_2);
        buttonsPanel.add(UserName_3);
        buttonsPanel.add(Invite_3);

// Create a panel for the title
        JPanel titlePanel = new JPanel();
        JLabel title = new JLabel(MatchViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        titlePanel.add(title);

// Create a panel for the back button
        JPanel backButtonPanel = new JPanel();
        Back = new JButton(MatchViewModel.BACK_BUTTON_LABEL);
        backButtonPanel.add(Back);

// Add components to the MatchView
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(titlePanel);
        this.add(buttonsPanel);
        this.add(backButtonPanel);


//        //      Title
//        JLabel title = new JLabel(MatchViewModel.TITLE_LABEL);
//
////      Might need to switch to title.setAlignmentY(Component.TOP_ALIGNMENT);
//        title.setAlignmentX(Component.CENTER_ALIGNMENT);
//
////      This is a collection of buttons
//        JPanel buttons = new JPanel();
//
//
////      Invite Buttons
//        Invite_1 = new JButton(MatchViewModel.INVITE_BUTTON_LABEL_1);
//        Invite_2 = new JButton(MatchViewModel.INVITE_BUTTON_LABEL_2);
//        Invite_3 = new JButton(MatchViewModel.INVITE_BUTTON_LABEL_3);
//        buttons.add(Invite_1);
//        buttons.add(Invite_2);
//        buttons.add(Invite_3);
//
////      Back Button
//        Back = new JButton(MatchViewModel.BACK_BUTTON_LABEL);
//        buttons.add(Back);
//
////      Matched Users
//
////      JLabels
//        UserName_1 = new JLabel(MatchViewModel.INVITE_BUTTON_LABEL_1);
//        UserName_2 = new JLabel(MatchViewModel.INVITE_BUTTON_LABEL_2);
//        UserName_3 = new JLabel(MatchViewModel.INVITE_BUTTON_LABEL_3);


        Invite_1.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent followButton) {
//                        check if the button was pushed
                        if (followButton.getSource().equals(Invite_1)) {
                            MatchState matchState = matchViewModel.getState();
                            sendInviteController.execute(matchState.getCLIENT_USERID(), matchState.getMATCHED_USERSID().get(0));
                        }
                    }
                }
        );

        Invite_2.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent followButton) {
//                        check if the button was pushed
                        if (followButton.getSource().equals(Invite_2)) {
                            MatchState matchState = matchViewModel.getState();
                            sendInviteController.execute(matchState.getCLIENT_USERID(), matchState.getMATCHED_USERSID().get(1));
                        }
                    }
                }
        );

        Invite_3.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent followButton) {
//                        check if the button was pushed
                        if (followButton.getSource().equals(Invite_3)) {
                            MatchState matchState = matchViewModel.getState();
                            sendInviteController.execute(matchState.getCLIENT_USERID(), matchState.getMATCHED_USERSID().get(2));
                        }
                    }
                }
        );

        Back.addActionListener(

                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent backButton) {
                        if (backButton.getSource().equals(Back)) {
//                          USE OTHER CONTROLLER TO BRING BACK TO LoggedInView
                            MatchState state = matchViewModel.getState();
                            homePageController.execute(state.getCLIENT_USERID());
                        }
                    }
                }
        );

    }
    @Override
    public void actionPerformed(ActionEvent e) {
//      This is for a button that has not been implemented
        JOptionPane.showConfirmDialog(this, "Not implemented yet.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        MatchState state = (MatchState) evt.getNewValue();
//      See if there's an error with MatchedUsers
        if (state.getMatchedUsersError() != null) {
//          Display Error Screen
            JOptionPane.showMessageDialog(this, state.getMatchedUsersError());
        }
    }
}