<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%-- 
    Document   : pegawaiTambah
    Created on : May 6, 2015, 3:34:56 PM
    Author     : fas
--%>
<%@include file="/konfig/konfig.jsp" %>
<%@include file="/WEB-INF/layout/header.jsp" %>

<div class="row">
    <table class="table borderless">
        <tr>
            <td>
                <!--                <h1>Daftar Karyawan <button type="submit" class="btn btn-success text-right" >Go</button></h1>-->
                <h1>
                    <span>Daftar Karyawan</span>
                </h1>
            </td>
            <td>
                <form class="pull-right form-inline">
                    <input type="text" class="form-control" id="search_karyawan" name="search_karyawan" 
                           style="text-align: right;" placeholder="Nama / No KTP"/>
                    <button class="btn btn-success">Cari</button>
                    <div class="btn btn-success" onclick="tambahKaryawan()">Tambah Karyawan</div>
                </form>
            </td>
            <!--            <td class="pull-right">
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
                        </td>  -->
        </tr>


    </table>  
    <table class="table table-striped">
        <thead>
            <tr>
                <th colspan="1"></th>
                <th colspan="1"></th>
                <th colspan="3">Nama Lengkap</th>
                <th colspan="1" style="text-align:center">Jumlah Kontrak</th>
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
                    <td colspan="1" style="text-align:center" onclick="ShowDetailPopUp();">
                        <c:out value="${karyawans.jumlah_kontrak}" />
                    </td>
                    <td colspan="1" style="text-align:center"><c:out value="${karyawans.kontrak_mulai}" /></td>
                    <td colspan="1" style="text-align:center"><c:out value="${karyawans.kontrak_berakhir}" /></td>
                    <td colspan="1" style="text-align:center"><c:out value="${karyawans.lama_kontrak}" /></td>
                    <td colspan="1">Rp</td>
                    <td colspan="1" class="numberfilter pull-right"><c:out value="${karyawans.gp_awal}" /></td>
                    <td colspan="1" style="text-align:center"><c:out value="${karyawans.total_hari}" /></td>
                </tr>
                <tr>
                    <td colspan="2">
                    <td colspan="10">
                        <div id="accordion<c:out value="${loop.index}" />" class="collapse">
                            <table width="100%">
                                <tr>
                                    <td valign="top" width="50%">
                                        <table class="table borderless">
                                            <tr>
                                                <td>Alamat</td>
                                                <td>  :  </td>
                                                <td><c:out value="${karyawans.alamat}" /></td>
                                            </tr>
                                            <tr>
                                                <td>Tempat/Tgl Lahir  </td>
                                                <td> : </td>
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
                                    </td>
                                    <td valign="top" width="50%">
                                        <form class="form-kontrak" method="post">
                                            <input type="hidden" id="indexKe" name="indexKe" value="<c:out value="${loop.index}"/>"/>
                                            <input type="hidden" id="kontrakKe" name="kontrakKe" value="<c:out value="${karyawans.jumlah_kontrak}"/>"/>
                                            
                                            <table class="table table-bordered" id="tableKontrak<c:out value="${loop.index}" />">
                                                <tr>
                                                    <th style="text-align:center" width="15%">Ke-</th>
                                                    <th style="text-align:center" width="28%"">Mulai</th>
                                                    <th style="text-align:center" width="28%">Berakhir</th>
                                                    <th style="text-align:center" width="29%">GP Awal</th>
                                                </tr>
                                                <c:forEach items="${karyawans.detail_kontrak}" var="kontraks" varStatus="loop2">
                                                    <tr>
                                                        <td style="text-align:center" width="15%"><c:out value="${loop2.index+1}" /></td>
                                                        <td style="text-align:center" width="28%"><fmt:formatDate type="date" pattern="dd-MM-yyyy" value="${kontraks.tanggal_mulai}" /></td>
                                                        <td style="text-align:center" width="28%"><fmt:formatDate type="date" pattern="dd-MM-yyyy" value="${kontraks.tanggal_berakhir}" /></td>
                                                        <td style="text-align:center" width="29%"><c:out value="${kontraks.gp_awal}" /></td>
                                                        <td class="hidden"><input type="hidden" name="id_karyawan" value="<c:out value="${karyawans.id_karyawan}"/>"></th>

                                                    </tr>
                                                </c:forEach>
                                                <tr id="tambahKontrak<c:out value="${loop.index}" />">
                                                    <td colspan="4" style="text-align:center">
    <!--                                                    <button type="submit" class="btn btn-success btn-add-kontrak" onclick="ClickAddContract(<c:out value='${loop.index}' />)">Tambah Kontrak Baru</button>-->
                                                        <button class="btn btn-success btn-add-kontrak" onclick="addRow(<c:out value='${loop.index}' />)">Tambah Kontrak Baru</button>
                                                    </td>
                                                </tr>
                                            </table>
                                        </form>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<script type="text/javascript">
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
                return false;
            }
        });
    }
    refreshautocompletekaryawan();

    function ShowDetailPopUp() {
        popup.Show();
    }
    function ClickAddContract(index) {
        komponen = '<tr>' +
                '<td style="text-align:center" valign="center" >' +
                '<span class="glyphicon glyphicon-ok glyphicon-positive cursor-pointer"></span> ' +
                '<span class="glyphicon glyphicon-remove glyphicon-negative cursor-pointer" onclick="deleteRow(' + index + ')"></span></td>' +
                '<td style="text-align:center"><input type="text" class="form-control datepickerWithYearRange" id="kontrakMulai" name="kontrakMulai" placeholder="Kontrak Mulai"></td>' +
                '<td style="text-align:center"><input type="text" class="form-control datepickerWithYearRange" id="kontrakBerakhir" name="kontrakBerakhir" placeholder="Kontrak Berakhir"></td>';
//        $('#tableKontrak').append(komponen);
        $(komponen).insertBefore('#tambahKontrak' + index);
    }


    function addRow(idxKar) {
        var table = document.getElementById('tableKontrak' + idxKar);
        var rowCount = table.rows.length;
        var row = table.insertRow(rowCount - 1);
        row.id = "tr_" + idxKar + '_' + (rowCount - 1);
        row.className = "tempinput";
        
        //$('#tr_' + idxKar + '_' + (rowCount - 1)').addClass("beroh");
        row.insertCell(0).innerHTML = '<td style="text-align:center" valign="center" width="15%">' +
                '<button type="submit" id="btn_save_' + idxKar + '_' + (rowCount - 1) + '" class="glyphicon glyphicon-ok glyphicon-positive cursor-pointer"></button>' +
                '<span id="btn_del_' + idxKar + '_' + (rowCount - 1) + '" class="glyphicon glyphicon-remove glyphicon-negative cursor-pointer" onclick="deleteRow(' + (rowCount - 1) + ',' + idxKar + ')"></span></td>';
        row.insertCell(1).innerHTML = '<td style="text-align:center" width="28%"><input type="text" class="form-control datepickerWithYearRange" id="kontrakMulai" name="kontrakMulai" placeholder="Kontrak Mulai"></td>';
        row.insertCell(2).innerHTML = '<td style="text-align:center" width="28%"><input type="text" class="form-control datepickerWithYearRange" id="kontrakBerakhir" name="kontrakBerakhir" placeholder="Kontrak Berakhir"/></td>';
        row.insertCell(3).innerHTML = '<td style="text-align:center" width="29%"><input type="text" class="form-control numberfilter" id="gp" name="gp" placeholder="GP"/></td>';
        //row.insertCell(3).innerHTML = '<td style="text-align:center" width="33%"><input type="text" class="form-control numberfilter" id="gp" name="gp" placeholder="GP"/></td>';
    }

    function addRowSuccess(jobj) {
        var table = document.getElementById('tableKontrak' + jobj.indexKe);
        var rowCount = table.rows.length;
        var row = table.insertRow(rowCount - 1);
        row.id = "tr_" + jobj.indexKe + '_' + (rowCount - 1);
        row.insertCell(0).innerHTML = '<td style="text-align:center" valign="center" width="15%">' + jobj.kontrakKe + '</td>';
        row.insertCell(1).innerHTML = '<td style="text-align:center" width="28%">' + jobj.tanggal_mulai + '</td>';
        row.insertCell(2).innerHTML = '<td style="text-align:center" width="28%">' + jobj.tanggal_berakhir + '</td>';
        row.insertCell(3).innerHTML = '<td style="text-align:center" width="29%">' + jobj.gp_awal + '</td>';
        $('.tempinput').empty();
    }

    function deleteRow(delIndex, idxKar) {
        try {
            var table = document.getElementById('tableKontrak' + idxKar);
//            for (i=delIndex+1; i<table.rows.length-1; i++) {
//                document.getElementById("btn_save_"+idxKar+"_"+i).id = "btn_save_"+idxKar+"_"+(i-1);
//            }
            $("#tr_" + idxKar + "_" + delIndex).remove();
//            table.deleteRow(delIndex);
        } catch (e) {
            alert(e);
        }
    }
    $('body').on('focus', ".datepickerWithYearRange", function () {
        $(this).datepicker(
                {
                    changeMonth: true,
                    changeYear: true,
                    showButtonPanel: true,
                    dateFormat: 'dd-mm-yy',
                    yearRange: "-100:+100", })
    });
    $('.form-kontrak').submit(function (e) {
        e.preventDefault();
        //var roww = document.getElementById('indexKe').;
        //var roww = $('#indexKe').text();
        var thisform = $(this);
        $.post('${baseURL}karyawan/specific/input', thisform.serialize(),
                function (data)
                {
                    var jobj = data;
                    if (jobj.error == "error") {
                        alert(jobj.message);
                    }
                    else
                    {
                        //deleteRow();
                        //alert(jobj.indexKe);
                        addRowSuccess(jobj);
                    }
                });
    });
    
    function tambahKaryawan() {
        location.href = "${baseURL}karyawan/input";
//window.location = "${baseURL}karyawan/input";
    }

</script>
<%@include file="/WEB-INF/layout/footer.jsp" %>
