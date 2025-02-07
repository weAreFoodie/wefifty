package controller;

import controller.action.Action;

public class ActionFactory {
	private static ActionFactory instance = new ActionFactory();

	private ActionFactory() {
	}

	public static ActionFactory getInstance() {
		return instance;
	}

	public Action getHomeAction(String command) { // list
		Action action = null;

		if (command.equals("")) {

		} else if (command.equals("")) {

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
}
