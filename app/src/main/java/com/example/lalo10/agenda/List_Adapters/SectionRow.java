package com.example.lalo10.agenda.List_Adapters;

/**
 * Created by lalo10 on 10/27/17.
 */

public class SectionRow<S,R> {

    private R row;
    private S section;
    private boolean isRow;

    private boolean isSection;

    public SectionRow() {

    }

    public SectionRow makeRow(R row) {
        SectionRow<S,R> newRow = new SectionRow<>();
        newRow.setRow(row);
        newRow.isRow = true;
        newRow.isSection = false;
        return newRow;
    }

    public SectionRow makeSection(S row) {
        SectionRow<S,R> newRow = new SectionRow<>();
        newRow.setSection(row);
        newRow.isSection = true;
        newRow.isRow = false;
        return newRow;
    }

    public void setRow(R row) {
        this.row = row;
    }

    public R getRow() {
        return row;
    }

    public void setSection(S section) {
        this.section = section;
    }

    public S getSection() {
        return section;
    }

    public boolean isRow() {
        return isRow;
    }


    public boolean isSection() {
        return isSection;
    }
}
