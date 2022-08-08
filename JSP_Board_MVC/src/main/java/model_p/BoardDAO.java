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

    public ArrayList<BoardDTO> list() {
        ArrayList<BoardDTO> res = new ArrayList<>();

        sql = "select * from board";


        try {
            ptmt = con.prepareStatement(sql);
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
