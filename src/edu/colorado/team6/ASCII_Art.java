package edu.colorado.team6;

import java.util.HashMap;

public class ASCII_Art {
    public static final HashMap<String, String> asciiArt = new HashMap<String, String>();

  static {
    final String champagne =
        "       ___\n"
            + "    .-     \\\n"
            + "   /   _    \\\n"
            + "  |  /   \\   \\\n"
            + "   \\|     \\   \\\n"
            + "    \\      \\   \\\n"
            + "     \\      \\   \\\n"
            + "      \\     _\\   \\\n"
            + "       \\  /       \\\n"
            + "        \\|         |\n"
            + "         \\  ~      |\n"
            + "          \\.__ ~   \\\n"
            + "              \\     \\\n"
            + "               \\     \\\n"
            + "                \\     \\\n"
            + "                 \\ ._=_\\\n"
            + "                  \\/    >\n"
            + "                   ` \\\\\\\n"
            + "                  .  \\\\\\\\   .\n"
            + "                 .    |||| .   .\n"
            + "                _________________\n"
            + "               <_________________>\n"
            + "                \\ _________o___ /\n"
            + "                  \\    o  .   /\n"
            + "                    \\   .   /\n"
            + "                      \\   /\n"
            + "                       | |\n"
            + "                       | |\n"
            + "                       | |\n"
            + "                       | |\n"
            + "                    ___| |___\n"
            + "                 _--         --_\n"
            + "                   -----------";
    asciiArt.put("Champagne", champagne);

    final String nuke =
        "                                ............\n"
            + "                           ..::::::::::::::::::..\n"
            + "                      ..:::::''    .....     ``:::::..\n"
            + "                    .::''' ....:::::::::::::....  ```::.\n"
            + "                  .::'   .:::::::::::::::::::::::.    `::.\n"
            + "                 ::    .:::::::::::::::::::::::::::.     ::\n"
            + "                ::     :::::::::::::::::::::::::::::      ::\n"
            + "               ::       :::'    `:::::::::'    `:::        ::\n"
            + "              .:                  `:::::'                   :.\n"
            + "              ::           ...:::::::::::::::...            ::\n"
            + "             .:               `````:::::'''''                :.\n"
            + "             ::                    :::::                     ::\n"
            + "            .:                     :::::                      :.\n"
            + "            ::                     :::::                      ::\n"
            + "          .::::..                  :::::                   ..::::.\n"
            + "         ::.o.`:::..           .:  :::::  :.          ..::::'.o.::\n"
            + "        :: HHHboo.`::::..      :::: ::: ::::       ..:::'.oodHHH ::\n"
            + "       :: dHHHHHHHboo.`::::.    `:::::::::'    ..:::'.oodHHHHHHHb ::\n"
            + "       : dHHHHHHHHHHHHboo.`:::. .. `:::' .. .:::'.oodHHHHHHHHHHHHb :\n"
            + "      :: HHHHHHHHH'OOOO`HHboo.   `::...::'   .oodHH'OOOO`HHHHHHHHH ::\n"
            + "      : dHHHHHHH'OOOOOOOO`HHHHboo. `:::' .oodHHHH'OOOOOOOO`HHHHHHHb :\n"
            + "     :: HHHHHHH'OOOOOOOOOO`HHHHHHH       HHHHHHH'OOOOOOOOOO`HHHHHHH ::\n"
            + "     : dHHHHHHHHHHHH||HHHHHHHHHHH'.dHHHb.`HHHHHHHHHHH||HHHHHHHHHHHHb :\n"
            + "    :: HHHHHHHHHHH--++--HHHHHHHHH HHHHHHH HHHHHHHHH--++--HHHHHHHHHHH ::\n"
            + "    :: HHHHHHHHHHHHH||HHHHHHHHHHH HHHHHHH HHHHHHHHHHH||HHHHHHHHHHHHH ::\n"
            + "    ::..........................  `8HHHP'  ..........................::\n"
            + "    `::::::::::::::::::::::::::  Hbo. .odH  ::::::::::::::::::::::::::'\n"
            + "                         `::::: .HHHHHHHHH. :::::'\n"
            + "                           `::: dHHHHHHHHHb :::'\n"
            + "                             :'.HHHHHHHHHHH.`:\n"
            + "                            .: dHHHLiveHHHHb :.\n"
            + "                            ::.HHHHHinHHHHHH.::\n"
            + "                           :: dHHHHFearHHHHHb ::\n"
            + "                           ::.HHHHHHHHHHHHHHH.::\n"
            + "                          :: dHHHHHHHHHHHHHHHb ::\n"
            + "                          ::.HHHHHHHHHHHHHHHHH.::\n"
            + "                         :: dHHHHHHHHHHHHHHHHHb ::\n"
            + "                         :: HHHHHHHHHHHHHHHHHHH ::\n"
            + "                         `::`\"*HHHKroggHHHHH*\"':;'\n"
            + "                           `-...-----------...-'";
    asciiArt.put("Nuke", nuke);

    final String pie =
              "                       (   )  )\n"
            + "                        )  ( )\n"
            + "                        .....\n"
            + "                     .:::::::::.\n"
            + "                     ~\\_______/~";
    asciiArt.put("Pie", pie);

    final String poo = "(   )\n" +
            "  (   ) (\n" +
            "   ) _   )\n" +
            "    ( \\_\n" +
            "  _(_\\ \\)__\n" +
            " (____\\___))";

    asciiArt.put("Poo", poo);

    final String ship = "                                     |__\n" +
            "                                     |\\/\n" +
            "                                     ---\n" +
            "                                     / | [\n" +
            "                              !      | |||\n" +
            "                            _/|     _/|-++'\n" +
            "                        +  +--|    |--|--|_ |-\n" +
            "                     { /|__|  |/\\__|  |--- |||__/\n" +
            "                    +---------------___[}-_===_.'____                 /\\\n" +
            "                ____`-' ||___-{]_| _[}-  |     |_[___\\==--            \\/   _\n" +
            " __..._____--==/___]_|__|_____________________________[___\\==--____,------' .7\n" +
            "|                                                                     BB-61/\n" +
            " \\_________________________________________________________________________|";

    asciiArt.put("Ship", ship);

  }

}
