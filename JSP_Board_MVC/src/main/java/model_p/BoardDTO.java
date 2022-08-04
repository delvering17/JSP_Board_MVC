package model_p;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class BoardDTO {

    int id;
    String title;
    String pname;
    String pw;
    String content;
    Date reg_date;
    int cnt;
    String upfile;
    int seq;
    int level;
    int gid;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 (E) HH:mm");

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getContent() {
        return content;
    }

    public String getContentBr() {

        return content.replaceAll("\n","<br/>");
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getReg_date() {
        return reg_date;
    }

    public String getReg_dateStr() {

        return sdf.format(reg_date);
    }

    public void setReg_date(Date reg_date) {
        this.reg_date = reg_date;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public String getUpfile() {
        return upfile;
    }

    public String getUpfileEn() {
        if(getUpfile() != null) {

            try {
                return URLEncoder.encode(upfile,"UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        }

        return null;
    }

    public boolean isImg() {
        String imgRegex = ".*[.](jpg|jpeg|png|bmp|pdf|gif)";

        return (getUpfile() != null & Pattern.matches(imgRegex,getUpfile().toLowerCase()));
    }
    public void setUpfile(String upfile) {
        this.upfile = upfile;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    @Override
    public String toString() {
        return "BoardDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", pname='" + pname + '\'' +
                ", pw='" + pw + '\'' +
                ", content='" + content + '\'' +
                ", reg_date=" + reg_date +
                ", cnt=" + cnt +
                ", upfile='" + upfile + '\'' +
                ", seq=" + seq +
                ", level=" + level +
                ", gid=" + gid +
                '}';
    }
}
