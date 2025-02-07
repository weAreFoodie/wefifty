package controller;

import controller.action.Action;
import controller.action.FriendRequestAction;

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
		} else if (command.equals("")) {

		}

		return action;
	}
}
