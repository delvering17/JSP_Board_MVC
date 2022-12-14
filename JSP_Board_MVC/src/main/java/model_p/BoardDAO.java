package model_p;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BoardDAO {

    Connection con;
    PreparedStatement ptmt;
    ResultSet rs;
    String sql;

    public BoardDAO() {
        try {
            Context init = new InitialContext();
            DataSource ds = (DataSource) init.lookup("java:comp/env/qwer");
            con = ds.getConnection();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public ArrayList<BoardDTO> list(int first, int limit) {
        ArrayList<BoardDTO> res = new ArrayList<>();

        sql = "select * from board order by gid desc, seq limit ?,?";


        try {
            ptmt = con.prepareStatement(sql);
            ptmt.setInt(1, first);
            ptmt.setInt(2, limit);

            rs = ptmt.executeQuery();

            while(rs.next()) {
                BoardDTO dto = new BoardDTO();

                dto.setId(rs.getInt("id"));
                dto.setTitle(rs.getString("title"));
                dto.setPname(rs.getString("pname"));
                dto.setReg_date(rs.getTimestamp("reg_date"));
                dto.setCnt(rs.getInt("cnt"));
                dto.setSeq(rs.getInt("seq"));
                dto.setLevel(rs.getInt("level"));
                dto.setGid(rs.getInt("gid"));

                res.add(dto);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close();
        }


        return res;
    }

    public void addCnt(int id) {
        sql = "update board set cnt = cnt + 1 where id = ?";

        try {
            ptmt = con.prepareStatement(sql);
            ptmt.setInt(1,id);
            ptmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public BoardDTO detail(int id) {
        BoardDTO res = null;

        sql = "select * from board where id = ?";

        try {
            ptmt = con.prepareStatement(sql);
            ptmt.setInt(1, id);
            rs = ptmt.executeQuery();

            if(rs.next()) {
                res = new BoardDTO();
                res.setId(rs.getInt("id"));
                res.setCnt(rs.getInt("cnt"));
                res.setSeq(rs.getInt("seq"));
                res.setLevel(rs.getInt("level"));
                res.setGid(rs.getInt("gid"));
                res.setTitle(rs.getString("title"));
                res.setPname(rs.getString("pname"));
                res.setContent(rs.getString("content"));
                res.setUpfile(rs.getString("upfile"));
                res.setReg_date(rs.getTimestamp("reg_date"));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close();
        }

        return res;
    }

    public void insert(BoardDTO dto) {

        sql = "select max(id)+1 from board";
        try {
            ptmt = con.prepareStatement(sql);
            rs = ptmt.executeQuery();
            rs.next();
            dto.id = rs.getInt(1);
            dto.gid = rs.getInt(1);



            sql = "insert into board (id, title, pname, pw, content, reg_date, cnt, upfile, seq, level, gid)"
                    + "values (?,?,?,?,?,sysdate(), -1,?,0,0,?)";

            ptmt =con.prepareStatement(sql);
            ptmt.setInt(1, dto.id);
            ptmt.setString(2, dto.title);
            ptmt.setString(3, dto.pname);
            ptmt.setString(4, dto.pw);
            ptmt.setString(5, dto.content);
            ptmt.setString(6, dto.upfile);
            ptmt.setInt(7, dto.gid);

            ptmt.executeUpdate();



        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close();
        }


    }

    public int delete(BoardDTO dto) {
        sql = "delete from board where id = ? and pw = ?";

        try {
            ptmt = con.prepareStatement(sql);
            ptmt.setInt(1,dto.id);
            ptmt.setString(2,dto.pw);

            return ptmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }

        return 0 ;
    }

    public int fileDelete(BoardDTO dto) {

        sql = "update board set upfile = null where id = ? and pw = ?";

        try {
            ptmt = con.prepareStatement(sql);
            ptmt.setInt(1, dto.id);
            ptmt.setString(2, dto.pw);

            return ptmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }

        return 0;
    }

    public int modify(BoardDTO dto) {

        try {
            sql = "update board set title = ?, pname = ?, content = ?, upfile = ? where id = ? and pw = ?";

            ptmt =con.prepareStatement(sql);
            ptmt.setString(1, dto.title);
            ptmt.setString(2, dto.pname);
            ptmt.setString(3, dto.content);
            ptmt.setString(4, dto.upfile);
            ptmt.setInt(5, dto.id);
            ptmt.setString(6, dto.pw);

            return ptmt.executeUpdate();


        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            close();
        }

        return 0;

    }

    public void reply(BoardDTO dto) {

        sql = "update board set seq = seq + 1 where gid = ? and seq > ?";
        try {
            ptmt = con.prepareStatement(sql);
            ptmt.setInt(1, dto.gid);
            ptmt.setInt(2, dto.seq);

            ptmt.executeUpdate();

            sql = "insert into board ( title, pname, pw, content, reg_date, cnt, upfile, seq, level, gid) "
                    + "values (?,?,?,?,sysdate(), -1,null,?,?,?)";

            ptmt = con.prepareStatement(sql);

            ptmt.setString(1, dto.title);
            ptmt.setString(2, dto.pname);
            ptmt.setString(3, dto.pw);
            ptmt.setString(4, dto.content);
            ptmt.setInt(5, dto.seq + 1);
            ptmt.setInt(6, dto.level + 1);
            ptmt.setInt(7, dto.gid);

            ptmt.executeUpdate();

            // ??? ??? ID ????????????
//            sql = "select max(id) from board";
//
//            ptmt = con.prepareStatement(sql);
//            rs = ptmt.executeQuery();
//            rs.next();
//            dto.id = rs.getInt(1);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public int total() {

        sql = "select count(*) from board";

        try {
            ptmt = con.prepareStatement(sql);
            rs = ptmt.executeQuery();

            rs.next();

            return rs.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }


    public void close() {
        if(rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if(ptmt != null) {
            try {
                ptmt.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if(con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

}
