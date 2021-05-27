package uk.joshiejack.simplyseasons.world.date;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;
import uk.joshiejack.penguinlib.util.helpers.minecraft.TimeHelper;

import javax.annotation.Nonnull;
import java.time.DayOfWeek;

public class CalendarDate implements INBTSerializable<CompoundNBT> {
    private DayOfWeek weekday = DayOfWeek.MONDAY;
    private int monthday = 1;
    private int year = 1;

    public CalendarDate() {}
    public CalendarDate(DayOfWeek weekday, int monthday, int year) {
        this.weekday = weekday;
        this.monthday = monthday;
        this.year = year;
    }

    @Nonnull
    public DayOfWeek getWeekday() {
        if (weekday == null) {
            weekday = DayOfWeek.MONDAY;
        }

        return weekday;
    }

    public int getDay() {
        return monthday;
    }

    public int getYear() {
        return year;
    }

    public void set(CalendarDate date) {
        this.weekday = date.weekday;
        this.monthday = date.monthday;
        this.year = date.year;
    }

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT tag = new CompoundNBT();
        tag.putByte("Weekday", (byte) getWeekday().ordinal());
        tag.putShort("Day", (short) monthday);
        tag.putShort("Year", (short) year);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundNBT tag) {
        weekday = DayOfWeek.values()[tag.getByte("Weekday")];
        monthday = tag.getShort("Day");
        year = tag.getShort("Year");
    }

    public static CalendarDate getFromTime(long time) {
        return new CalendarDate(TimeHelper.getWeekday(time), 1 + DateHelper.getDay(time), 1 + DateHelper.getYear(time));
    }
}
