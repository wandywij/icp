<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 
    Document   : pegawaiTambah
    Created on : May 6, 2015, 3:34:56 PM
    Author     : fas
--%>
<%@include file="/konfig/konfig.jsp" %>
<%@include file="/WEB-INF/layout/header.jsp" %>

<div class="row">
    <table class="table">
        <colgroup>
            <col width="140"/>
            <col/>
            <col width="140" />
        </colgroup>
        <tr>
            <td>
                <!--                <h1>Daftar Karyawan <button type="submit" class="btn btn-success text-right" >Go</button></h1>-->
                <h1>
                    <span>Daftar Karyawan</span>
                </h1>
            </td>


            <td class="pull-right">
                <button type="submit" class="btn btn-success">Tambah Karyawan</button>
            </td>

            <td class="pull-right">
                <button class="btn btn-success">Cari</button>
            </td>


            <td class="pull-right">
                <div>
                    <input type="text" class="form-control" id="search_karyawan" name="search_karyawan" 
                           style="text-align: right;" placeholder="Nama / No KTP"/>


                </div>

            </td>  


            <!--            <td class="text-right " >
                            <button type="submit" class="btn btn-success text-right" >Go</button>
                        </td>-->
        </tr>


    </table>

    <div id="content">
        <table class="table table-striped">
            <thead>
                <tr>
                    <th colspan="1"></th>
                    <th colspan="1"></th>
                    <th colspan="3">Nama Lengkap</th>
                    <th colspan="1" style="text-align:center">Kontrak ke-</th>
                    <th colspan="1" style="text-align:center">Kontrak Mulai</th>
                    <th colspan="1" style="text-align:center">Kontrak Berakhir</th>
                    <th colspan="1" style="text-align:center">Lama Kontrak</th>
                    <th colspan="2" style="text-align:center">Gaji Pokok</th>
                    <th colspan="1" style="text-align:center">Total Hari</th>
                </tr>
            </thead>

            <tbody>
                <c:forEach items="${karyawans}" var="karyawans" varStatus="loop">
                    <tr data-toggle="collapse" data-target="#accordion<c:out value="${loop.index}" />" class="clickable">
                        <td colspan="1">
                            <c:choose>
                                <c:when test="${karyawans.warning_type == '2'}">
                                    <span class="glyphicon glyphicon-warning-sign glyphicon-warning-contract-end"></span>
                                </c:when>
                                <c:when test="${karyawans.warning_type == '1'}">
                                    <span class="glyphicon glyphicon-warning-sign glyphicon-warning-almost-due"></span>
                                </c:when>
                                <c:when test="${karyawans.warning_type == '0'}">
                                </c:when>
                            </c:choose>
                        </td>
                        <td colspan="1">></td>
                        <td colspan="3"><c:out value="${karyawans.nama_karyawan}" /></td>
                        <td colspan="1" style="text-align:center"><c:out value="${karyawans.jumlah_kontrak}" /></td>
                        <td colspan="1" style="text-align:center"><c:out value="${karyawans.kontrak_mulai}" /></td>
                        <td colspan="1" style="text-align:center"><c:out value="${karyawans.kontrak_berakhir}" /></td>
                        <td colspan="1" style="text-align:center"><c:out value="${karyawans.lama_kontrak}" /></td>
                        <td colspan="1">Rp</td>
                        <td colspan="1" class="numberfilter pull-right"><c:out value="${karyawans.gp_awal}" /></td>
                        <td colspan="1" style="text-align:center"><c:out value="${karyawans.total_hari}" /></td>
                    </tr>
                    <tr>
                        <td colspan="2">
                        <td colspan="">
                            <div id="accordion<c:out value="${loop.index}" />" class="collapse">
                                <table>
                                    <tr>
                                        <td>Alamat</td>
                                        <td>  :  </td>
                                        <td><c:out value="${karyawans.alamat}" /></td>
                                    </tr>
                                    <tr>
                                        <td>Tempat/Tgl Lahir  </td>
                                        <td>:</td>
                                        <td><c:out value="${karyawans.tempat_lahir}" />, <c:out value="${karyawans.tgl_lahir}" /></td>
                                    </tr>
                                    <tr>
                                        <td>No KTP </td>
                                        <td>  :  </td>
                                        <td><c:out value="${karyawans.no_ktp}" /></td>
                                    </tr>
                                    <tr>
                                        <td>Bagian</td>
                                        <td>  :  </td>
                                        <td><c:out value="${karyawans.bagian}" /></td>
                                    </tr>
                                    <tr>
                                        <td>Keterangan</td>
                                        <td>  :  </td>
                                        <td><c:out value="${karyawans.keterangan}" /></td>
                                    </tr>
                                </table>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<script>
    var cacheKaryawan = {};
    function refreshautocompletekaryawan() {
        $('#search_karyawan').autocomplete({
            minLength: 0,
            source: function (request, response) {
                var term = request.term;
                if (term[0] == "(" && term.indexOf(")", 1) != -1)
                    request.term = term = term.substring(1, term.indexOf(")", 1));
                if (term in cacheKaryawan) {
                    response(cacheKaryawan[ term ]);
                    return;
                }

                $.getJSON("${baseURL}karyawan.json", request, function (data, status, xhr) {
                    cacheKaryawan[ term ] = data;
                    response(data);
                });
            },
            select: function (event, ui) {
                var tmp = ui.item.value;
                var tmp2 = tmp.indexOf(")", 1);
                var kode = tmp.substring(1, tmp2);
                var nama = tmp.substring(tmp2 + 2);
//                $("#kodebarang_text").html(kode);
//                $("#kodebarang").val(kode);
//                $("#namabarang").val(nama);
                return false;
            }
        });
    }

    refreshautocompletekaryawan();
</script>

<%@include file="/WEB-INF/layout/footer.jsp" %>
