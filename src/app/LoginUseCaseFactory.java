package app;

import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.home_page.HomePageViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import use_case.login.*;
import view.LoginView;

import javax.swing.*;
import java.io.IOException;

public class LoginUseCaseFactory {

    /** Prevent instantiation. */
    private LoginUseCaseFactory() {}

    public static LoginView create(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            HomePageViewModel homePageViewModel,
            LoginUserDataAccessInterface userDataAccessObject,
            LoginSpotifyAPIDataAccessInterface spotifyAPIDataAccessInterface) {

        try {
            LoginController loginController = createLoginUseCase(viewManagerModel, loginViewModel, homePageViewModel,
                    userDataAccessObject, spotifyAPIDataAccessInterface);
            return new LoginView(loginViewModel, loginController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static LoginController createLoginUseCase(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            HomePageViewModel homePageViewModel,
            LoginUserDataAccessInterface userDataAccessObject,
            LoginSpotifyAPIDataAccessInterface spotifyAPIDataAccessObject) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel, homePageViewModel, loginViewModel);

        UserFactory userFactory = new CommonUserFactory();

        LoginInputBoundary loginInteractor = new LoginInteractor(
                userDataAccessObject, spotifyAPIDataAccessObject, loginOutputBoundary, userFactory);

        return new LoginController(loginInteractor);
    }

}
