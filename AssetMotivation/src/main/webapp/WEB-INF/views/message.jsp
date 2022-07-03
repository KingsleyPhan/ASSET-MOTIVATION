<div>
	<c:if test="${ERROR != null}">
		<div class="alert alert-danger message">
			<strong id="error">${ERROR}</strong>
		</div>
	</c:if>
	<c:if test="${NOTIFICATION != null}">
		<div class="alert alert-success message">
			<strong>${NOTIFICATION}</strong>
		</div>
	</c:if>

</div>