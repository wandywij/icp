<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 
    Document   : pegawaiTambah
    Created on : May 6, 2015, 3:34:56 PM
    Author     : fas
--%>
<%@include file="/konfig/konfig.jsp" %>
<%@include file="/WEB-INF/layout/header.jsp" %>

<div class="row">
    <h1>Input Bagian</h1>

    <form class="form-horizontal" method="post">
        <div class="form-group">
            <div class="row">
                <div class="col-xs-4">
                    <input type="text" class="form-control" id="nama_departemen" name="nama_departemen" placeholder="Nama Bagian">
                </div>
                <div class="col-xs-2">
                    <button type="submit" class="btn btn-success">Tambah</button>
                </div>
            </div>
        </div>
    </form>

    <ul class ="list-group col-xs-4">
        <c:forEach items="${departemens}" var="data1">
            <li class="list-group-item"><c:out value="${data1.nama_departemen}"/></li>

        </c:forEach>
    </ul>

<!--    <div class="row">
        <div class="col-xs-8">
            <table class="table">
                <colgroup>
                    <col width="50" />
                    <col width="100" />
                </colgroup>
                <thead>
                    <tr>
                        <th>Kode Departemen</th>
                        <th>Nama Departemen</th>
                    </tr>
                </thead>
                <tbody>

                    <c:forEach items="${departemens}" var="data1">
                        <tr>
                            <td><c:out value="${data1.id_departemen}"/></td>
                            <td><c:out value="${data1.nama_departemen}"/></td>
                            
                        </tr>                    
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>-->
    <!--            <ul class="list-group col-xs-4">
                    <li class="list-group-item">Produksi</li>
                    <li class="list-group-item">Mekanik</li>
                    <li class="list-group-item">Perlengkapan</li>
                    <li class="list-group-item">Printing</li>
                    <li class="list-group-item">Gudang</li>
                </ul>-->

</div>

<%@include file="/WEB-INF/layout/footer.jsp" %>
