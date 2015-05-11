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
        <div class="col-xs-12">
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
            
            <ul class="list-group col-xs-4">
                <li class="list-group-item">Produksi</li>
                <li class="list-group-item">Mekanik</li>
                <li class="list-group-item">Perlengkapan</li>
                <li class="list-group-item">Printing</li>
                <li class="list-group-item">Gudang</li>
            </ul>
	</div>
</div>

<%@include file="/WEB-INF/layout/footer.jsp" %>
