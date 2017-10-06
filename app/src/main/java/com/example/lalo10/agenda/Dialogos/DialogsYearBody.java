package com.example.lalo10.agenda.Dialogos;

import java.util.List;

/**
 * Created by lalo10 on 8/1/17.
 */

public abstract class DialogsYearBody {
    public abstract List<CharSequence> getModels(CharSequence year);
    public abstract void actionInClick(CharSequence year, CharSequence body);
}
