package com.douzone.mysite.board;

import com.douzone.web.mvc.Action;
import com.douzone.web.mvc.ActionFactory;

public class BoardActionFactory extends ActionFactory{

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		if("modify".equals(actionName)) {
			action = new ModifyAction();
		} else if("view".equals(actionName)){
			action = new ViewAction();
		} else if("write".equals(actionName)){
			action = new WriteAction(); 
		} else if("add".equals(actionName)){
			action = new AddAction(); 
		} else if("update1".equals(actionName)){
			action = new Update1Action(); 
		} else if("delete1".equals(actionName)){
			action = new Delete1Action(); 
		} else if("rewrite".equals(actionName)){
			action = new ReWriteAction(); 
		}else {
			action = new BoardAction();
		}
		return action;
	}


}
