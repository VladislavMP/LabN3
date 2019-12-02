package lab3.varb4;

    import javax.swing.table.AbstractTableModel;
    @SuppressWarnings("serial")
    public class GornerTableModel extends AbstractTableModel {
        private Double[] coefficients;
        private Double from;
        private Double to;
        private Double step;
        private double result[] = new double[1];
        private Boolean resultb;
        private int xc;
        private int resultc;
        public GornerTableModel(Double from, Double to, Double step,

                                Double[] coefficients) {

            this.from = from;
            this.to = to;
            this.step = step;
            this.coefficients = coefficients;
        }
        public Double getFrom() {
            return from;
        }
        public Double getTo() {
            return to;
        }
        public Double getStep() {
            return step;
        }
        public int getColumnCount() {
// В данной модели два столбца
            return 3;
        }
        public int getRowCount() {
// Вычислить количество точек между началом и концом отрезка
// исходя из шага табулирования
            return new Double(Math.ceil((to-from)/step)).intValue()+1;
        }
        public Object getValueAt(int row, int col) {
// Вычислить значение X как НАЧАЛО_ОТРЕЗКА + ШАГ*НОМЕР_СТРОКИ
            double x = from + step*row;
            if (col==0) {
// Если запрашивается значение 1-го столбца, то это X
                return x;
            } else if (col==1){
                result[0] = 0.0;
                for(int i = 0; i < coefficients.length; i++){
                    result[0] += Math.pow(x, coefficients.length-1-i)*coefficients[i];
                }
                return result[0];
            } else {
                result[0] = 0.0;
                for(int i = 0; i < coefficients.length; i++){
                    result[0] += Math.pow(x, coefficients.length-1-i)*coefficients[i];
                }
                xc = (int)Math.floor(x);
                xc = Math.abs(xc);
                resultc = (int)Math.floor(result[0]);
                resultc = Math.abs(resultc);
                if (xc == 1 || resultc == 1 || xc == 0 || resultc == 0)
                    return resultb = true;
                else{
                    int c;
                    while (xc > 0) {
                        c = resultc % xc;
                        resultc = xc;
                        xc = c;
                    }
                    if (resultc == 1) resultb = true;
                    else resultb = false;
                    return resultb;
                }

            }
        }

        public String getColumnName(int col) {
            switch (col) {
                case 0:
// Название 1-го столбца
                    return "Значение X";
                case 1:
// Название 2-го столбца
                    return "Значение многочлена";
                default:
                    return "Взаимно простые?";
            }
        }
        public Class<?> getColumnClass(int col) {
            switch (col) {
                case 0:
                    return Double.class;
                case 1:
                    return Double.class;
                default:
                    return Boolean.class;
            }
        }
    }

