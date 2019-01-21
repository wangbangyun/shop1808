package entity;

import com.sun.org.apache.xpath.internal.compiler.Keywords;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Page<T> implements Serializable {
        private int currentPage;
        private int pageSize;
        private int totalPage;
        private List<Goods> glist;
        private String url;
        private String keyword;

}
