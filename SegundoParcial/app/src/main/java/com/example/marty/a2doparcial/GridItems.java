package com.example.marty.a2doparcial;

/**
 * Created by MARTY on 18/10/2017.
 */

public class GridItems {
    private String nombre;
    private int idDrawable;

    public GridItems(String nombre, int idDrawable) {
        this.nombre = nombre;
        this.idDrawable = idDrawable;
    }

    public String getNombre() {
        return nombre;
    }

    public int getIdDrawable() {
        return idDrawable;
    }

    public int getId() {
        return nombre.hashCode();
    }

    public static GridItems[] ITEMS = {
            new GridItems("Jaguar F-Type 2015", R.drawable.jaguar_f_type_2015),
            new GridItems("Mercedes AMG-GT", R.drawable.mercedes_benz_amg_gt),
            new GridItems("Mazda MX-5", R.drawable.mazda_mx5_2015),
            new GridItems("Porsche 911 GTS", R.drawable.porsche_911_gts),
            new GridItems("BMW Serie 6", R.drawable.bmw_serie6_cabrio_2015),
            new GridItems("Ford Mondeo", R.drawable.ford_mondeo),
            new GridItems("Volvo V60 Cross Country", R.drawable.volvo_v60_crosscountry),
            new GridItems("Jaguar XE", R.drawable.jaguar_xe),
            new GridItems("VW Golf R Variant", R.drawable.volkswagen_golf_r_variant_2015),
            new GridItems("Seat León ST Cupra", R.drawable.seat_leon_st_cupra),
    };

    /**
     * Obtiene item basado en su identificador
     *
     * @param id identificador
     * @return GridItems
     */
    public static GridItems getItem(int id) {
        for (GridItems item : ITEMS) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }
}
