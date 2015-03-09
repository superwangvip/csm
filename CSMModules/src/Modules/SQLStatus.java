package Modules;
/**
 *
 * @author Administrator
 */
class SQLStatus {

    private boolean status = false;//false表示未执行完毕,true表示执行完毕

    public SQLStatus() {
    }

    public void setSTatus(boolean status) {
        this.status = status;
    }

    public boolean getStatus() {
        return this.status;
    }
}
