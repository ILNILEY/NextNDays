package homework;

class Row{
    MyDate inputDate;
    int n;
    MyDate outputDate;
    public Row(){

    }
    public Row(MyDate inputDate, int n, MyDate outputDate) {
        this.inputDate = inputDate;
        this.n = n;
        this.outputDate = outputDate;
    }

    public MyDate getInputDate() {
        return this.inputDate;
    }
        public void setInputDate(MyDate inputDate) {this.inputDate = inputDate;}
        public int getN() {return this.n;}
        public void setN(int n) {this.n = n;}
        public MyDate getOutputDate() {return this.outputDate;}
        public void setOutputDate(MyDate outputDate) {this.outputDate = outputDate;}
}
