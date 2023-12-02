package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.home_page.HomePageController;
import interface_adapter.home_page.HomePagePresenter;
import interface_adapter.home_page.HomePageViewModel;
import interface_adapter.match.MatchController;
import interface_adapter.match.MatchPresenter;
import interface_adapter.match.MatchViewModel;
import use_case.home_page.HomePageInputBoundary;
import use_case.home_page.HomePageInteracter;
import use_case.home_page.HomePageOutPutBoundary;
import use_case.match.*;
import view.MatchView;

import java.io.IOException;

public class MatchFactory {

    public static MatchView create(ViewManagerModel viewManagerModel, MatchViewModel matchViewModel, HomePageViewModel homePageViewModel, MatchUserAccessInterface matchUserAccessInterface) throws IOException {

        MatchController matchController = createMatchController(viewManagerModel, matchViewModel, matchUserAccessInterface);
        HomePageController homePageController = createHomePageController(viewManagerModel, homePageViewModel);
        return new MatchView(matchViewModel, matchController, homePageViewModel, homePageController);
    }
    private static MatchController createMatchController(ViewManagerModel viewManagerModel, MatchViewModel matchViewModel, MatchUserAccessInterface matchUserAccessInterface) {

        MatchOutputBoundary presenter = new MatchPresenter(matchViewModel, viewManagerModel);

        MatchInputobundary userMatchInteracter = new MatchInteractor(presenter, matchUserAccessInterface);

        return new MatchController(userMatchInteracter);
    }

    private static HomePageController createHomePageController(ViewManagerModel viewManagerModel, HomePageViewModel homePageViewModel) {

        HomePageOutPutBoundary homePagePresenter = new HomePagePresenter(homePageViewModel, viewManagerModel);

        HomePageInputBoundary homePageInteracter = new HomePageInteracter(homePagePresenter);

        return new HomePageController(homePageInteracter);
    }
}

