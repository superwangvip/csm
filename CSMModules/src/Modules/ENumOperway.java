//操作方式枚举类,实现操作方式代码与名称的转换。

package Modules;

/**
 *
 * @author Jiang Youquan
 */
   enum EnumOperway {

        OPERATE_WAY_0("0", "柜台委托"),
        OPERATE_WAY_1("1", "电话银行"),
        OPERATE_WAY_2("2", "电话外线"),
        OPERATE_WAY_3("3", "刷卡委托"),
        OPERATE_WAY_4("4", "热键委托"),
        OPERATE_WAY_5("5", "远程委托"),
        OPERATE_WAY_6("6", "新太委托"),
        OPERATE_WAY_7("7", "恒生委托"),
        OPERATE_WAY_8("8", "大连银行"),
        OPERATE_WAY_9("9", "宏汇委托"),
        OPERATE_WAY_a("a", "手机"),
        OPERATE_WAY_b("b", "免费电话"),
        OPERATE_WAY_c("c", "网上委托"),
        OPERATE_WAY_d("d", "手机委托"),
        OPERATE_WAY_e("e", "通达信现场"),
        OPERATE_WAY_f("f", "特殊机构外围操作");
        private final String operate_code;
        private final String operate_way;

        EnumOperway(String operate_code, String operate_way) {
            this.operate_code = operate_code;
            this.operate_way = operate_way;
        }

        @Override
        public String toString() {
            return this.operate_way;
        }

        String getOperateWayCombination(String operateCode) {
            String combinationWay = "";
            for (int i = 0; i < operateCode.length(); i++) {
                char code = operateCode.charAt(i);
                if (code == '0') {
                    if (!combinationWay.contains(OPERATE_WAY_0.toString())) {
                        combinationWay = combinationWay + OPERATE_WAY_0.toString();
                    }
                } else if (code == '1') {
                    if (!combinationWay.contains(OPERATE_WAY_1.toString())) {
                        combinationWay = combinationWay + "," + OPERATE_WAY_1.toString();
                    }
                } else if (code == '2') {
                    if (!combinationWay.contains(OPERATE_WAY_2.toString())) {
                        combinationWay = combinationWay + "," + OPERATE_WAY_2.toString();
                    }
                } else if (code == '3') {
                    if (!combinationWay.contains(OPERATE_WAY_3.toString())) {
                        combinationWay = combinationWay + "," + OPERATE_WAY_3.toString();
                    }
                } else if (code == '4') {
                    if (!combinationWay.contains(OPERATE_WAY_4.toString())) {
                        combinationWay = combinationWay + "," + OPERATE_WAY_4.toString();
                    }
                } else if (code == '5') {
                    if (!combinationWay.contains(OPERATE_WAY_5.toString())) {
                        combinationWay = combinationWay + "," + OPERATE_WAY_5.toString();
                    }
                } else if (code == '6') {
                    if (!combinationWay.contains(OPERATE_WAY_6.toString())) {
                        combinationWay = combinationWay + "," + OPERATE_WAY_6.toString();
                    }
                } else if (code == '7') {
                    if (!combinationWay.contains(OPERATE_WAY_7.toString())) {
                        combinationWay = combinationWay + "," + OPERATE_WAY_7.toString();
                    }
                } else if (code == '8') {
                    if (!combinationWay.contains(OPERATE_WAY_8.toString())) {
                        combinationWay = combinationWay + "," + OPERATE_WAY_8.toString();
                    }
                } else if (code == '9') {
                    if (!combinationWay.contains(OPERATE_WAY_9.toString())) {
                        combinationWay = combinationWay + "," + OPERATE_WAY_9.toString();
                    }
                } else if (code == 'a') {
                    if (!combinationWay.contains(OPERATE_WAY_a.toString())) {
                        combinationWay = combinationWay + "," + OPERATE_WAY_a.toString();
                    }
                } else if (code == 'b') {
                    if (!combinationWay.contains(OPERATE_WAY_b.toString())) {
                        combinationWay = combinationWay + "," + OPERATE_WAY_b.toString();
                    }
                } else if (code == 'c') {
                    if (!combinationWay.contains(OPERATE_WAY_c.toString())) {
                        combinationWay = combinationWay + "," + OPERATE_WAY_c.toString();
                    }
                } else if (code == 'd') {
                    if (!combinationWay.contains(OPERATE_WAY_d.toString())) {
                        combinationWay = combinationWay + "," + OPERATE_WAY_d.toString();
                    }
                } else if (code == 'e') {
                    if (!combinationWay.contains(OPERATE_WAY_e.toString())) {
                        combinationWay = combinationWay + "," + OPERATE_WAY_e.toString();
                    }
                } else if (code == 'f') {
                    if (!combinationWay.contains(OPERATE_WAY_f.toString())) {
                        combinationWay = combinationWay + "," + OPERATE_WAY_f.toString();
                    }
                }
            }
            return combinationWay;
        }

        public static void main(String[] args) {
            System.out.println("0123456789abcdef:" + EnumOperway.OPERATE_WAY_0.getOperateWayCombination("0123456789abcdef"));
            EnumOperway p = EnumOperway.OPERATE_WAY_0;
            System.out.println("0123456789abcdef:" + p.getOperateWayCombination("0123456789abcdef"));
        }
    }
