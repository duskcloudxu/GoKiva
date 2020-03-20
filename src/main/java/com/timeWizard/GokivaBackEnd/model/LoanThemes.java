package com.timeWizard.GokivaBackEnd.model;

public class LoanThemes {
	protected String ThemeId;
	protected String Theme;
	public LoanThemes(String themeId, String theme) {
		super();
		ThemeId = themeId;
		Theme = theme;
	}
	public String getThemeId() {
		return ThemeId;
	}
	public void setThemeId(String themeId) {
		ThemeId = themeId;
	}
	public String getTheme() {
		return Theme;
	}
	public void setTheme(String theme) {
		Theme = theme;
	}


}
