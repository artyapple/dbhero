package com.dbteam.dbhero;

import com.beust.jcommander.Parameter;

public class BasicConfig {
	public static final String DESC_H = "Print help";
    @Parameter(names = {"", "--help"}, help = true, description = DESC_H)
    public boolean help;

    public static final String DESC_TT = "Telegram token";
    @Parameter(names = {"--tt"}, required = true, description = DESC_TT)
    public String telegramToken;

}
