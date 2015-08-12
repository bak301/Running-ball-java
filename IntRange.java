public class IntRange
{
    public int min,max;
    public IntRange(int x,int y)
    {
        this.min = x;
        this.max = y;
    }
    public boolean contain(int num)
    {
        return num < this.max && num > this.min;
    }
}
