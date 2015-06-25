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