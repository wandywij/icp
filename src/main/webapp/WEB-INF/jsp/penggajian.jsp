<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 
    Document   : penggajian
    Created on : Jun 5, 2015, 1:22:57 AM
    Author     : fas
--%>

<%@include file="/konfig/konfig.jsp" %>
<%@include file="/WEB-INF/layout/header.jsp" %>

<div class="row">
    <h1>Input Gaji Karyawan</h1>
    <div class="col-xs-12">
        <form class="form-horizontal" method="post">
            <div class="form-group">
                <label for="nama_karyawan" class="col-xs-2 control-label">No Urut</label>
                <div class="col-xs-10">
                    <input type="text" class="form-control" id="no_urut" name="no_urut" placeholder="No. Urut">
                </div>
            </div>
            <div class="form-group">
                <label for="nama_karyawan" class="col-xs-2 control-label">Nama Karyawan</label>
                <div class="col-xs-10">
                    <input type="text" class="form-control" id="nama_karyawan" name="nama_karyawan" placeholder="Nama Karyawan">
                </div>
            </div>
            <div class="form-group">
                <label for="alamat" class="col-xs-2 control-label">No Absen Sidik Jari</label>
                <div class="col-xs-10">
                    <input type="text" class="form-control" id="no_fingerprint" name="no_fingerprint" placeholder="No. Absen Sidik Jari">
                </div>
            </div>
            <div class="form-group">
                <label for="tempat_tgl_lahir" class="col-xs-2 control-label">Tempat/Tanggal Lahir</label>
                <div class="row">
                    <div class="col-xs-3">
                        <input type="text" class="form-control" id="tempat_lahir" name="tempat_lahir" placeholder="Tempat Lahir">
                    </div>
                    <div class="col-xs-4">
                        <input type="text" class="form-control datepickerWithYearRange" id="tanggal_lahir" name="tanggal_lahir" placeholder="Tanggal Lahir">
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label for="no_ktp" class="col-xs-2 control-label">No KTP</label>
                <div class="col-xs-10">
                    <input type="text" class="form-control" id="no_ktp" name="no_ktp" placeholder="Nomor KTP">
                </div>
            </div>
            <div class="form-group">
                <label for="bagian" class="col-xs-2 control-label">Bagian</label>
                <div class="col-xs-10">
                    <select name="bagian">
                        <c:forEach items="${departemens}" var="departemens">
                            <option value=<c:out value="${departemens.id_departemen}"/>>
                                <c:out value="${departemens.nama_departemen}"/>
                                </options>
                            </c:forEach>
                    </select>
                    <!--                    <option>Produksi</option>
                                        <option>Mekanik</option>
                                        <option>Perlengkapan</option>
                                        <option>Printing</option>
                                        <option>Gudang</option>-->
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label for="kontrak_mulai" class="col-xs-2 control-label">Kontrak Mulai</label>
                <div class="col-xs-10">
                    <input type="text" class="form-control datepickerWithYearRange" id="kontrak_mulai" name="kontrak_mulai" placeholder="Tanggal Kontrak Mulai">
                </div>
            </div>
            <div class="form-group">
                <label for="kontrak_berakhir" class="col-xs-2 control-label">Kontrak Berakhir</label>
                <div class="col-xs-10">
                    <input type="text" class="form-control datepickerWithYearRange" id="kontrak_berakhir" name="kontrak_berakhir" placeholder="Tanggal Kontrak Berakhir">
                </div>
            </div>
            <div class="form-group">
                <label for="gp_awal" class="col-xs-2 control-label">GP Awal</label>
                <div class="col-xs-10">
                    <input type="text" class="form-control" id="gp_awal" name="gp_awal" placeholder="GP Awal">
                </div>
            </div>
            <div class="form-group">
                <label for="no_absen" class="col-xs-2 control-label">No Absen</label>
                <div class="col-xs-10">
                    <input type="text" class="form-control" id="no_absen" name="no_absen" placeholder="No Absen">
                </div>
            </div>
            <div class="form-group">
                <label for="keterangan" class="col-xs-2 control-label">Keterangan</label>
                <div class="col-xs-10">
                    <input type="text" class="form-control" id="keterangan" name="keterangan" placeholder="Keterangan">
                </div>
            </div>
            <div class="form-group">
                <div class="col-xs-offset-2 col-xs-10">
                    <button type="submit" class="btn btn-success">Submit</button>
                </div>
            </div>
        </form>
    </div>
</div>

<%@include file="/WEB-INF/layout/footer.jsp" %>
