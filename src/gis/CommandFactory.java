package gis;

import gis.action.*;

public class CommandFactory {
	private CommandFactory(){}
	public static CommandFactory getInstance(){
		return (new CommandFactory());
	}
	public IAction getAction(String Command)
	{
		IAction action=null;
		
		System.out.println("getAction:"+Command);
		
		//요청에 따른 Action 반환
		switch (Command) {
		
		case "SelectCampsite":
			action = new SelectCampsiteAction();
			break;
		case "SelectRegion":
			action = new SelectRegionAction();
			break;
		case "SelectTheme":
			action = new SelectThemeAction();
			break;
		case "SelectIntegration":
			action = new SelectIntegrationAction();
			break;
		//Command가 기본 값일 경우
		case "DefaultCampsite":
		default:
			action= new DefaultCampsiteAction();
			
			break;
		}
		
		return action;
	}
}
