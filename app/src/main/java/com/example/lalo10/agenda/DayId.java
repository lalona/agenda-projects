package com.example.lalo10.agenda;

import android.content.Context;

/**
 * Created by lalo10 on 10/5/17.
 */

public class DayId {
    public String nombre;
    public int id;

    public DayId(String nombre,int id) {
        this.nombre = nombre;
        this.id = id;
    }

    public static DayId[] getArrayDays(Context context) {
        // 7 porque los dias de la semana nunca van a cambiar y si cambian que perro
        return  new DayId[] {
                new DayId(context.getResources().getString(R.string.DOMINGO),  0),
                new DayId(context.getResources().getString(R.string.LUNES),    1),
                new DayId(context.getResources().getString(R.string.MARTES),   2),
                new DayId(context.getResources().getString(R.string.MIERCOLES),3),
                new DayId(context.getResources().getString(R.string.JUEVES),   4),
                new DayId(context.getResources().getString(R.string.VIERNES),  5),
                new DayId(context.getResources().getString(R.string.SABADO),   6)
        };
    }

    @Override
    public String toString() {
        return nombre;
    }
}
