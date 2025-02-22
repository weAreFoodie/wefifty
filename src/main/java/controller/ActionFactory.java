package controller;

import controller.action.Action;
import controller.action.FriendRecommendationAction;
import controller.action.ViewSignUpAction;
import controller.action.FriendRequestAction;
import controller.action.GetFriendCardAction;
import controller.action.GetFriendListAction;
import controller.action.GetFriendRequestListAction;
import controller.action.GetNavProfileAction;
import controller.action.UpdateFriendRequestAction;
import controller.action.LoginAction;
import controller.action.PaymentAction;
import controller.action.PointChargingAction;
import controller.action.UpdateProfileAction;
import controller.action.ViewPointChargingAction;


public class ActionFactory {
	private static ActionFactory instance = new ActionFactory();

	private ActionFactory() {}

	public static ActionFactory getInstance() {
		return instance;
	}

	public Action getHomeAction(String command) { // list
		Action action = null;

		if (command.equals("FriendRequest")) {
			action = new FriendRequestAction();
		} else if (command.equals("GetFriendList")) {
			action = new GetFriendListAction();
		} else if (command.equals("GetFriendRequestList")) {
			action = new GetFriendRequestListAction();
		} else if (command.equals("GetFriendCard")) {
			action = new GetFriendCardAction();
		} else if (command.equals("UpdateFriendRequest")) {
			action = new UpdateFriendRequestAction();
		} else if (command.equals("viewPointCharging")) {
			action = new ViewPointChargingAction();
		} else if (command.equals("payment")) {
			action = new PaymentAction();
		} else if (command.equals("pointCharging")) {
			action = new PointChargingAction();
		} else if (command.equals("updateProfile")) {
			action = new UpdateProfileAction();
		} else if (command.equals("friendRecommendation")) {
			action = new FriendRecommendationAction();
		} else if (command.equals("GetNavProfileAction")) {
			action = new GetNavProfileAction();
		}

		return action;
	}
	
	public Action getSignUpAction(String command) {
		Action action = null;
		
		if(command.equals("login")) {
			
		}else if(command.equals("signup")) {
			action = new ViewSignUpAction();
		}
		
		return action;
	}
	
	public Action getLoginAction(String command) {
		Action action = null;
		if(command.equals("login")) {
			action = new LoginAction();
		}else {
			
		}
		
		return action;
	}
}
